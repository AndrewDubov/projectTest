package andrew.dubov.ecommerce14.fragments;

import andrew.dubov.ecommerce14.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProgressFragment extends Fragment {
	
	public static final String TAG = "ProgressFragment";
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.d(TAG, TAG + " onAttach()");
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, TAG + " onCreate()");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_store_front, container, false);
		Log.d(TAG, TAG + " onCreateView()");
		return view;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		Log.d(TAG, TAG + " onViewCreated()");
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.d(TAG, TAG + " onActivityCreated()");
	}
	
	@Override
	public void onStart() {
		super.onStart();
		Log.d(TAG, TAG + " onStart()");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, TAG + " onResume()");
	}
	
	@Override
	public void onPause() {
		super.onPause();
		Log.d(TAG, TAG + " onPause()");
	}
	
	@Override
	public void onStop() {
		super.onStop();
		Log.d(TAG, TAG + " onStop()");
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.d(TAG, TAG + " onDestroyView()");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, TAG + " onDestroy()");
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		Log.d(TAG, TAG + " onDetach()");
	}
}
