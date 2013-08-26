package st40611.app.mytwitterapp;

import st40611.app.mytwitterapp.fragments.DiscoverListFragment;
import st40611.app.mytwitterapp.fragments.HomeTimelineFragment;
import st40611.app.mytwitterapp.fragments.MentionsFragment;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

public class TimelineActivity extends FragmentActivity implements TabListener {
	HomeTimelineFragment homeTimelineFragment;
	MentionsFragment mentionsFragment;
	DiscoverListFragment discoverListFragment;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		setupNavigationTabs();
	}
	
	private void setupNavigationTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);
		Tab tabHome = actionBar.newTab().setText("Home")
				.setTag("HomeTimelineFragment")
				.setIcon(R.drawable.ic_home)
				.setTabListener(this);
		
		Tab tabDiscover = actionBar.newTab().setText("Discover")
				.setTag("DiscoverListFragment")
				.setIcon(R.drawable.ic_search)
				.setTabListener(this);
		
		Tab tabMentions = actionBar.newTab().setText("Mentions")
				.setTag("MentionsFragment")
				.setIcon(R.drawable.ic_mentions)
				.setTabListener(this);
		
		actionBar.addTab(tabHome);
		actionBar.addTab(tabDiscover);
		actionBar.addTab(tabMentions);
		actionBar.selectTab(tabHome);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timeline, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_compose:
	            composeMessage();
	            return true;
	        case R.id.profile:
	        	viewProfile();
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void viewProfile() {
		Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
		startActivity(i);
	}
	
	public void composeMessage() {
		Intent i = new Intent(getApplicationContext(), ComposeActivity.class);
		startActivity(i);
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		FragmentManager manager = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction fts = manager.beginTransaction();
		if (("HomeTimelineFragment").equals(tab.getTag())) {
			// set the fragment in FrameLayout to homeTimeLine
			homeTimelineFragment = new HomeTimelineFragment();
			fts.replace(R.id.frame_container, homeTimelineFragment);
		} else if (("MentionsFragment").equals(tab.getTag())){
			mentionsFragment = new MentionsFragment();
			fts.replace(R.id.frame_container, mentionsFragment);
		} else if (("DiscoverListFragment").equals(tab.getTag())){
			discoverListFragment = new DiscoverListFragment();
			fts.replace(R.id.frame_container, discoverListFragment);
		}
		fts.commit();
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		
	}
}
