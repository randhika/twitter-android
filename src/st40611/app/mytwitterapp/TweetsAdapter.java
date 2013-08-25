package st40611.app.mytwitterapp;

import java.util.List;

import st40611.app.restclienttemplate.models.Tweet;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import st40611.app.mytwitterapp.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class TweetsAdapter extends ArrayAdapter<Tweet> {
	
	public TweetsAdapter(Context context, List<Tweet> tweets) {
		super(context, 0, tweets);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.tweet_item, null);
		}
		
		Tweet tweet = getItem(position);
		
		ImageView image = (ImageView) view.findViewById(R.id.lvProfile);
		ImageLoader.getInstance().displayImage(tweet.getUser().getProfileImageUrl(), image);
		
		TextView name = (TextView) view.findViewById(R.id.tvName);
		String formattedName = "<b>" + tweet.getUser().getName() + "</b>" + " <small><font color='#777777'>@" + 
				tweet.getUser().getScreenName() + "</font></small>";
		name.setText(Html.fromHtml(formattedName));
		
		TextView body = (TextView) view.findViewById(R.id.tvBody);
		body.setText(Html.fromHtml(tweet.getBody()));
		
		return view;
	}
	
}
