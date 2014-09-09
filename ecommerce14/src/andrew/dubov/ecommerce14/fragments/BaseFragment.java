package andrew.dubov.ecommerce14.fragments;

import andrew.dubov.ecommerce14.activities.MainActivity;
import android.app.Activity;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {
	
	protected MainActivity mainActivity = null;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);		
		mainActivity = ((MainActivity) activity);
	}
	
	@Override
	public void onDetach() {
		super.onDetach();		
		mainActivity = null;
	}
}
