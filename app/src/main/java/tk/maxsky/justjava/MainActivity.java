/*
 * Copyright (c) 2016. MaxSky.TK. All Rights Reserved.
 */

package tk.maxsky.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
	// 初始化默认咖啡数量
	int numOfCoffees = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		// noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void submitOrder(View view) {
		if (numOfCoffees > 0) {
			String msg = "您点的 " + numOfCoffees + " 杯咖啡马上就到，不要急哈~ \n一共消费 RMB：" + numOfCoffees * 5 + " 元。";
			Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
			displayMessage(msg);
		} else {
			String msg = "你都不点一个也不付钱我送空气呢？滚(ノ｀Д)ノ";
			Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
			displayMessage(msg);
		}
	}

	private void display(int number) {
		TextView quantityTextView = (TextView) findViewById(R.id.quantity);
		quantityTextView.setText("" + number);
	}

	private void displayPrice(int number) {
		TextView priceTextView = (TextView) findViewById(R.id.price);
		priceTextView.setText("" + NumberFormat.getCurrencyInstance().format(number));
	}

	private void displayMessage(String message) {
		TextView msgTextView = (TextView) findViewById(R.id.message);
		msgTextView.setText(message);
	}

	public void increment(View view) {
		display(numOfCoffees += 1); // numOfCoffees +=1 等同于 numOfCoffees = numOfCoffees + 1
		displayPrice(numOfCoffees * 5);
	}

	public void decrement(View view) {
		if (numOfCoffees - 1 >= 0) {    // 如果 numOfCoffees - 1 后小于 0，就不继续减下去了，避免出现 -1 等情况
			display(numOfCoffees -= 1); // numOfCoffees -=1 等同于 numOfCoffees = numOfCoffees - 1
			displayPrice(numOfCoffees * 5);
		} else {
			Toast.makeText(this, "莫非你想倒送我几杯咖啡吗？走开(ノ｀Д)ノ", Toast.LENGTH_SHORT).show();
		}
	}
}
