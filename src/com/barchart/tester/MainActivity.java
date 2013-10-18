package com.barchart.tester;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.barchart.tester.pager.InfinitePagerAdapter;
import com.barchart.tester.pager.PagerContainer;

/**
 * Demo application showing how to mimic "Gallery" like behavior.
 * 
 * @author m-ehrenberg
 * 
 */
public class MainActivity extends Activity {

	private PagerContainer container;
	private ViewPager pager;
	private PagerAdapter adapter;
	private PagerAdapter wrappedAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		container = (PagerContainer) findViewById(R.id.pager_container);

		pager = container.getViewPager();
		adapter = new MyPagerAdapter();

		// Necessary or the pager will only have one extra page to show
		// make this at least however many pages you can see
		pager.setOffscreenPageLimit(adapter.getCount());

		// A little space between pages
		pager.setPageMargin(15);

		// wrap pager to provide infinite paging with wrap-around
		wrappedAdapter = new InfinitePagerAdapter(adapter);

		// If hardware acceleration is enabled, you should also remove
		// clipping on the pager for its children.
		pager.setClipChildren(false);

		pager.setAdapter(wrappedAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private class MyPagerAdapter extends PagerAdapter {

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			final TextView view = (TextView) getLayoutInflater().inflate(
					R.layout.sample_view, null);

			view.setText("Item " + position);

			container.addView(view);

			// keep reference to your views here
			//

			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public int getCount() {
			return 12;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return (view == object);
		}
	}

}
