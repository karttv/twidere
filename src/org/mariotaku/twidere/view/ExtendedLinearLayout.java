/*
 *				Twidere - Twitter client for Android
 * 
 * Copyright (C) 2012 Mariotaku Lee <mariotaku.lee@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mariotaku.twidere.view;

import org.mariotaku.twidere.util.ExtendedViewGroupInterface;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public final class ExtendedLinearLayout extends LinearLayout implements ExtendedViewGroupInterface {

	private TouchInterceptor mTouchInterceptor;
	private OnSizeChangedListener mOnSizeChangedListener;

	public ExtendedLinearLayout(final Context context) {
		super(context);
	}

	public ExtendedLinearLayout(final Context context, final AttributeSet attrs) {
		super(context, attrs);
	}

	public ExtendedLinearLayout(final Context context, final AttributeSet attrs, final int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean onInterceptTouchEvent(final MotionEvent event) {
		if (mTouchInterceptor != null) {
			final boolean ret = mTouchInterceptor.onInterceptTouchEvent(this, event);
			if (ret) return true;
		}
		return super.onInterceptTouchEvent(event);
	}

	@Override
	public boolean onTouchEvent(final MotionEvent event) {
		if (mTouchInterceptor != null) {
			final boolean ret = mTouchInterceptor.onTouchEvent(this, event);
			if (ret) return true;
		}
		return super.onTouchEvent(event);
	}

	@Override
	public void setOnSizeChangedListener(final OnSizeChangedListener listener) {
		mOnSizeChangedListener = listener;
	}

	@Override
	public void setTouchInterceptor(final TouchInterceptor listener) {
		mTouchInterceptor = listener;
	}

	@Override
	protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		if (mOnSizeChangedListener != null) {
			mOnSizeChangedListener.onSizeChanged(this, w, h, oldw, oldh);
		}
	}

}
