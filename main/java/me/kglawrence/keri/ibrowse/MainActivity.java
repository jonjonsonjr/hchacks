/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.kglawrence.keri.ibrowse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import me.kglawrence.keri.ibrowse.barcodereader.BarcodeCaptureActivity;

/**
 * Main activity demonstrating how to pass extra parameters to an activity that
 * reads barcodes.
 */
public class MainActivity extends Activity implements View.OnClickListener {

  // use a compound button so either checkbox or switch widgets work.
  private TextView statusMessage;
  private TextView barcodeValue;
  private GridLayout gridLayout;
  private TextView number;

  private static final int RC_BARCODE_CAPTURE = 9001;
  private static final String TAG = "BarcodeMain";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    statusMessage = (TextView) findViewById(R.id.status_message);
    barcodeValue = (TextView) findViewById(R.id.barcode_value);
    gridLayout = (GridLayout) findViewById(R.id.grid);
    number = (TextView) findViewById(R.id.text);

    for (int i = 0; i < gridLayout.getChildCount(); i++) {
      Button b = (Button) gridLayout.getChildAt(i);
      Log.d(TAG, b.toString());
      b.setOnClickListener(this);
    }

    number.setOnClickListener(this);
    findViewById(R.id.read_barcode).setOnClickListener(this);
  }

  /**
   * Called when a view has been clicked.
   *
   * @param v The view that was clicked.
   */
  @Override
  public void onClick(View v) {
    if (v.getId() == R.id.read_barcode) {
      //new ISBNLookupTask().execute("9780060589462");
      new AlertDialog.Builder(this)
          .setMessage("Are you " + getUser(number.getText().toString()) + "?")
          .setIcon(android.R.drawable.ic_dialog_alert)
          .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
              String userId = number.getText().toString();

              // launch barcode activity.
              Intent intent = new Intent(MainActivity.this, BarcodeCaptureActivity.class);
              intent.putExtra(BarcodeCaptureActivity.UserId, userId);
              startActivityForResult(intent, RC_BARCODE_CAPTURE);
            }})
          .setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              number.setText("");
            }
          }).show();
    } else if (v.getId() == R.id.text) {
      number.setText("");
    } else {
      Button b = (Button) v;
      String text = b.getText().toString();
      Log.d(TAG, text + " clicked");

      number.append(text);

      String id = number.getText().toString();

      String user = getUser(id);
    }
  }

  private String getUser(String id) {
    if (id.equals("1")) {
      return "Alice";
    } else if (id.equals("2")) {
      return "Bob";
    } else if (id.equals("3")) {
      return "Carol";
    } else {
      return "user-" + id;
    }
  }

  /**
   * Called when an activity you launched exits, giving you the requestCode
   * you started it with, the resultCode it returned, and any additional
   * data from it.  The <var>resultCode</var> will be
   * {@link #RESULT_CANCELED} if the activity explicitly returned that,
   * didn't return any result, or crashed during its operation.
   * <p/>
   * <p>You will receive this call immediately before onResume() when your
   * activity is re-starting.
   * <p/>
   *
   * @param requestCode The integer request code originally supplied to
   *                    startActivityForResult(), allowing you to identify who this
   *                    result came from.
   * @param resultCode  The integer result code returned by the child activity
   *                    through its setResult().
   * @param data        An Intent, which can return result data to the caller
   *                    (various data can be attached to Intent "extras").
   * @see #startActivityForResult
   * @see #createPendingResult
   * @see #setResult(int)
   */
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == RC_BARCODE_CAPTURE) {
      if (resultCode == CommonStatusCodes.SUCCESS) {
        if (data != null) {
          Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
          String userId = data.getStringExtra(BarcodeCaptureActivity.UserId);

          statusMessage.setText(userId + " read barcode");
          barcodeValue.setText(barcode.displayValue);
          Log.d(TAG, "Barcode read: " + barcode.displayValue);

          //chooch(barcode.displayValue);
        } else {
          statusMessage.setText(R.string.barcode_failure);
          Log.d(TAG, "No barcode captured, intent data is null");
        }
      } else {
        statusMessage.setText(String.format(getString(R.string.barcode_error),
            CommonStatusCodes.getStatusCodeString(resultCode)));
      }
    } else {
      super.onActivityResult(requestCode, resultCode, data);
    }
  }
}
