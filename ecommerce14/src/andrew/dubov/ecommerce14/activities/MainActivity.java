package andrew.dubov.ecommerce14.activities;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import andrew.dubov.ecommerce14.R;
import andrew.dubov.ecommerce14.fragments.BackEndFragment;
import andrew.dubov.ecommerce14.fragments.FragmentType;
import andrew.dubov.ecommerce14.fragments.NavigationFragment;
import andrew.dubov.ecommerce14.fragments.ProductNewFragment;
import andrew.dubov.ecommerce14.fragments.StoreFrontFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;

public class MainActivity extends FragmentActivity implements OnBackStackChangedListener, OnClickListener {

	private static final String LOG_TAG = "MainActivity";
	
	private FragmentManager fm = null;
	private FragmentTransaction ft = null;
	
	private StoreFrontFragment storeFrontFragment = null;
	private BackEndFragment backEndFragment = null;
	private ProductNewFragment productNewFragment = null;
	private NavigationFragment navigationFragment = null;
	
	private int backStackSize = 0;
	private boolean backStackEnable = true;
	
	private FrameLayout frameLayout = null;
	
	private Lock lock01 = null;
	
	{
		lock01 = new ReentrantLock();
		Log.i(LOG_TAG, LOG_TAG + " { }");
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {	    	  	    
	    super.onSaveInstanceState(outState);
	    
	    outState.putInt("backStackSize", backStackSize);
	    
	    Log.d(LOG_TAG, LOG_TAG + " onSaveInstanceState()");
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		frameLayout = (FrameLayout) findViewById(R.id.activity_main_frame_layout_content);
		
		fm = getSupportFragmentManager();
	    fm.addOnBackStackChangedListener(this);
		
		storeFrontFragment = (StoreFrontFragment) fm.findFragmentByTag(StoreFrontFragment.TAG);
		backEndFragment = (BackEndFragment) fm.findFragmentByTag(BackEndFragment.TAG);
		productNewFragment = (ProductNewFragment) fm.findFragmentByTag(ProductNewFragment.TAG);
		navigationFragment = (NavigationFragment) fm.findFragmentByTag(NavigationFragment.TAG);
		
		if(storeFrontFragment == null) {
			Log.e(LOG_TAG, LOG_TAG + " onCreate storeFrontFragment == null");
			storeFrontFragment = new StoreFrontFragment();
		}
		if(backEndFragment == null) {
			Log.e(LOG_TAG, LOG_TAG + " onCreate backEndFragment == null");
			backEndFragment = new BackEndFragment();
		}
		if(productNewFragment == null) {
			Log.e(LOG_TAG, LOG_TAG + " onCreate productNewFragment == null");
			productNewFragment = new ProductNewFragment();
		}
		if(navigationFragment == null) {			
			Log.e(LOG_TAG, LOG_TAG + " onCreate navigationFragment == null");
			navigationFragment = new NavigationFragment();			
		}
		if(savedInstanceState == null) {
			showFragment(FragmentType.STOREFRONT);
			Log.i(LOG_TAG, LOG_TAG + " onCreate savedInstanceState == null");
		} else {
			backStackSize = savedInstanceState.getInt("backStackSize");
			Log.i(LOG_TAG, LOG_TAG + " onCreate savedInstanceState != null");
		}
		Log.e(LOG_TAG, LOG_TAG + " onCreate() frameLayout.getChildCount()=" + frameLayout.getChildCount());
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
	    super.onRestoreInstanceState(savedInstanceState);
	    Log.d(LOG_TAG, LOG_TAG + " onRestoreInstanceState()");
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub		
		switch(view.getId()) {
			case R.id.btn_store_front: {
				Log.d(LOG_TAG, LOG_TAG + " btn_store_front");
				showFragment(FragmentType.STOREFRONT);
			} break;
			case R.id.btn_back_end: {
				Log.d(LOG_TAG, LOG_TAG + " btn_back_end");
				showFragment(FragmentType.BACKEND);
			} break;
			case R.id.back_end_btn_product_new: {
				Log.d(LOG_TAG, LOG_TAG + " back_end_btn_product_new");
				showFragment(FragmentType.PRODUCTNEW);
			} break;
			case R.id.product_new_btn_cancel: {
				Log.d(LOG_TAG, LOG_TAG + " product_new_btn_cancel");
				popBackStack();
			} break;
			case R.id.product_new_btn_save: {
				Log.d(LOG_TAG, LOG_TAG + " product_new_btn_save");
				popBackStack();
			} break;
		}
	}
	
	/**
	 * Gets form back stack a fragment
	 */
	private void popBackStack() {
		lock01.lock();
		try {
			backStackEnable = false;
			if(backStackSize > 0) {
				getSupportFragmentManager().popBackStack();
			}
		} finally {
			lock01.unlock();
		}
	}
	
	/**
	 * Shows a fragment, which needs for user
	 * @param fragment
	 */
	public void showFragment(FragmentType fragment) {
		lock01.lock();
		try {
			if(backStackEnable == false) {				
				return;
			}
			if(fragment == FragmentType.STOREFRONT) {
				if(backStackSize == 1) {
					popBackStack();
				} else {
					ft = fm.beginTransaction();
//					ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
					
					if(fm.findFragmentByTag(NavigationFragment.TAG) == null) {					
	//					fm.executePendingTransactions();
	//					ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
						ft.add(R.id.activity_main_frame_layout_navigation, navigationFragment, NavigationFragment.TAG);							
					}
					if(fm.findFragmentByTag(StoreFrontFragment.TAG) == null) {
	//					fm.executePendingTransactions();
	//					ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
						ft.add(R.id.activity_main_frame_layout_content, storeFrontFragment, StoreFrontFragment.TAG);
					}
					ft.commit();			
				}
			}
			else if(fragment == FragmentType.BACKEND) {				
				ft = fm.beginTransaction();					
//				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				
				if(fm.findFragmentByTag(NavigationFragment.TAG) == null) {				
	//				fm.executePendingTransactions();
	//				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);				
					ft.add(R.id.activity_main_frame_layout_navigation, navigationFragment, NavigationFragment.TAG);				
				}
				if(backEndFragment.isAdded() == false) {
					backStackEnable = false;
	//				fm.executePendingTransactions();
	//				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
					ft.add(R.id.activity_main_frame_layout_content, backEndFragment, BackEndFragment.TAG);
					ft.addToBackStack(BackEndFragment.TAG);				
				}
				ft.commit();
			}
			else if(fragment == FragmentType.PRODUCTNEW) {				
				ft = fm.beginTransaction();
//				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				
				if(fm.findFragmentByTag(NavigationFragment.TAG) != null) {						
	//				fm.executePendingTransactions();
	//				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
					ft.remove(navigationFragment);		
				}
				if(productNewFragment.isAdded() == false) {
					backStackEnable = false;
	//				fm.executePendingTransactions();
	//				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
					ft.add(R.id.activity_main_frame_layout_content, productNewFragment, ProductNewFragment.TAG);
					ft.addToBackStack(ProductNewFragment.TAG);
				}
				ft.commit();
			}
			else if(fragment == FragmentType.PROGRESS) {
				
			}
			Log.e(LOG_TAG, LOG_TAG + "showFragment() frameLayout.getChildCount()=" + frameLayout.getChildCount());
		} finally {
			lock01.unlock();
		}					
	}

	@Override
	public void onBackStackChanged() {
		// TODO Auto-generated method stub
		lock01.lock();
		try {
			if((backStackSize - fm.getBackStackEntryCount()) > 0) {
				switch(backStackSize) {
					case 1: {
						Log.e(LOG_TAG, "onBackStackChanged() FragmentType.STOREFRONT" + fm.getBackStackEntryCount() + " backStackSize=" + backStackSize);
					//	showFragment(FragmentType.STOREFRONT);
					} break;
					case 2: {
						Log.e(LOG_TAG, "onBackStackChanged() FragmentType.BACKEND" + fm.getBackStackEntryCount() + " backStackSize=" + backStackSize);
					//	showFragment(FragmentType.BACKEND);						
					} break;
				}				
			} 
			backStackSize = fm.getBackStackEntryCount();
			backStackEnable = true;
		} finally {
			lock01.unlock();
		}
	}
}
