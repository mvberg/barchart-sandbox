package com.barchart.tester;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.barchart.tester.pager.InfinitePagerAdapter;
import com.barchart.tester.pager.PagerContainer;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private Activity context;
	private Map<String, List<String>> symbolCollection;
	private List<String> symbols;

	public ExpandableListAdapter(Activity context, List<String> symbols,
			Map<String, List<String>> symbolCollection) {
		this.context = context;
		this.symbolCollection = symbolCollection;
		this.symbols = symbols;
	}

	public Object getChild(int groupPosition, int childPosition) {
		return symbolCollection.get(symbols.get(groupPosition)).get(
				childPosition);
	}

	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	public View getChildView(final int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		LayoutInflater inflater = context.getLayoutInflater();

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_pager, null);
			configurePager(convertView);
		}

		return convertView;
	}

	public int getChildrenCount(int groupPosition) {
		return symbolCollection.get(symbols.get(groupPosition)).size();
	}

	public Object getGroup(int groupPosition) {
		return symbols.get(groupPosition);
	}

	public int getGroupCount() {
		return symbols.size();
	}

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {

		String laptopName = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.item_symbol, null);
		}
		TextView item = (TextView) convertView.findViewById(R.id.textView1);
		item.setTypeface(null, Typeface.BOLD);
		item.setText(laptopName);
		return convertView;
	}

	public boolean hasStableIds() {
		return false;
	}

	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

	private void configurePager(final View view) {

		PagerContainer container = (PagerContainer) view
				.findViewById(R.id.pager_container);

		ViewPager pager = container.getViewPager();
		MyPagerAdapter adapter = new MyPagerAdapter();

		// Necessary or the pager will only have one extra page to show
		// make this at least however many pages you can see
		pager.setOffscreenPageLimit(adapter.getCount());

		// A little space between pages
		pager.setPageMargin(15);

		// wrap pager to provide infinite paging with wrap-around
		InfinitePagerAdapter wrappedAdapter = new InfinitePagerAdapter(adapter);

		// If hardware acceleration is enabled, you should also remove
		// clipping on the pager for its children.
		pager.setClipChildren(false);

		pager.setOnTouchListener(mSuppressInterceptListener);

		pager.setAdapter(wrappedAdapter);

	}

	private OnTouchListener mSuppressInterceptListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN
					&& v instanceof ViewGroup) {
				((ViewGroup) v).requestDisallowInterceptTouchEvent(true);
			}
			return false;
		}
	};

	private class MyPagerAdapter extends PagerAdapter {

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			final TextView view = (TextView) context.getLayoutInflater()
					.inflate(R.layout.sample_view, null);

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