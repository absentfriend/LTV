package com.fongmi.android.ltv.network.task;

import android.os.Handler;
import android.os.Looper;

import com.fongmi.android.ltv.bean.Channel;
import com.fongmi.android.ltv.impl.AsyncCallback;
import com.fongmi.android.ltv.network.Connector;
import com.fongmi.android.ltv.source.Force;
import com.fongmi.android.ltv.source.TvBus;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DynamicTask {

	private ExecutorService executor;
	private AsyncCallback callback;
	private final Handler handler;

	public static DynamicTask create(AsyncCallback callback) {
		return new DynamicTask(callback);
	}

	public DynamicTask(AsyncCallback callback) {
		this.executor = Executors.newSingleThreadExecutor();
		this.handler = new Handler(Looper.getMainLooper());
		this.callback = callback;
	}

	public DynamicTask run(Channel item) {
		executor.submit(() -> doInBackground(item));
		return this;
	}

	private void doInBackground(Channel item) {
		try {
			String url = item.getUrl();
			if (item.isTvBus()) TvBus.get().start(callback, item.getUrl());
			else if (item.isForce()) Force.get().start(callback, item.getUrl());
			else onPostExecute(Connector.link(url).getPath());
		} catch (Exception e) {
			onPostExecute(item.getUrl());
		}
	}

	private void onPostExecute(String url) {
		handler.post(() -> {
			if (callback != null) callback.onResponse(url);
		});
	}

	public void cancel() {
		if (executor != null) executor.shutdownNow();
		executor = null;
		callback = null;
	}
}