package cn.crane.application.greenlife.adapter.merchant;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import cn.crane.application.greenlife.R;
import cn.crane.framework.adapter.CommonAdapter;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：May 20, 2015 10:27:43 PM
 * @param <T>
 * 
 */
public class ListMerchantAdapter<T> extends CommonAdapter<T> implements OnClickListener {

	public ListMerchantAdapter(Context context, List<T> list) {
		super(context, list);
	}
	
	@Override
	public int getCount() {
		return 10;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if(convertView == null)
		{
			view = LayoutInflater.from(context).inflate(R.layout.item_merchant_list, null);
		}else
		{
			view = convertView;
		}
		Object object = getItem(position);
		view.setTag(R.id.tag_data, object);
		view.setOnClickListener(this);
			
		return view;
	}
	
	
	@Override
	public void onClick(View v) {
		if(onItemClickListener != null)
		{
			onItemClickListener.onItemClick(v.getTag(R.id.tag_data));
		}
	}
	
	private OnItemClickListener onItemClickListener;
	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}
	public interface OnItemClickListener
	{
		void onItemClick(Object object);
	}


}
