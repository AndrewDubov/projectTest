package andrew.dubov.ecommerce14.fragments;

import andrew.dubov.ecommerce14.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ProductNewFragment extends BaseFragment {

	public static final String TAG = "ProductNewFragment";
	
	private Button btnSave = null;
	private Button btnCancel = null;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.d(TAG, TAG + " onAttach()");
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		Log.d(TAG, TAG + " onCreate()");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_product_new, container, false);
		Log.d(TAG, TAG + " onCreateView()");
		return view;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		btnSave = (Button) view.findViewById(R.id.product_new_btn_save);
		btnSave.setOnClickListener(mainActivity);
		btnCancel = (Button) view.findViewById(R.id.product_new_btn_cancel);
		btnCancel.setOnClickListener(mainActivity);
		
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
