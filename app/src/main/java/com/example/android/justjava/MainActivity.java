package com.example.android.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;


import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    int numOfCoffees = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void submitOrder(View view) {


        if (numOfCoffees > 0 && numOfCoffees <= 100) {

            displayMessage();
            Button submitButton = (Button) findViewById(R.id.submit_button);
            submitButton.setVisibility(View.INVISIBLE);
            Button sendEmailButton = (Button) findViewById(R.id.email);
            sendEmailButton.setVisibility(View.VISIBLE);

        } else {
            TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
            priceTextView.setText("Ooops! That's not a valid number ");


        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */

    public void displayMessage() {
        TextView priceSummaryTextView = (TextView) findViewById(R.id.price_summary_text_view);
        priceSummaryTextView.setText("ORDER SUMMARY");

        int toppingsCost = 0;
        int whippedCreamCost = 1;
        int chocolateToppingsCost = 2;
        String whippedCream = "No";
        String chocolate = "No";

        CheckBox firstToppings = (CheckBox) findViewById(R.id.checkbox1);
        CheckBox secondToppings = (CheckBox) findViewById(R.id.checkbox2);
        EditText name = (EditText) findViewById(R.id.text_field);

        if (firstToppings.isChecked()) {

            whippedCream = "Yes";
            toppingsCost = toppingsCost + whippedCreamCost;


        }


        if (secondToppings.isChecked()) {

            chocolate = "Yes";
            toppingsCost = toppingsCost + chocolateToppingsCost;


        }


        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText("Order placed ! Here's your order summary:" + "\n" + "\n" + "\nName: " + name.getText().toString() + "\n" + "\nNumber of Coffees: " + numOfCoffees + "\n" + "\nAdd whipped cream ? " + whippedCream + "\n" + "\nAdd chocolate ? " + chocolate + "\n" + "\nTotal amount: $" + (numOfCoffees * (5 + toppingsCost)) + "\n" + "\n" +
                "\nThank you and enjoy your coffee! :)");

    }


    public void composeEmail() {

        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this

        intent.putExtra(Intent.EXTRA_SUBJECT, "Your Order Summary");
        intent.putExtra(Intent.EXTRA_TEXT, priceTextView.getText().toString());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void sendEmail(View view) {

        composeEmail();

    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);


        quantityTextView.setText("" + number + "");
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance(Locale.getDefault()).format(number));

    }

    public void increment(View view) {
        TextView priceSummaryTextView = (TextView) findViewById(R.id.price_summary_text_view);
        priceSummaryTextView.setText("PRICE (excluding toppings)");
        Button sendEmailButton = (Button) findViewById(R.id.email);
        sendEmailButton.setVisibility(View.INVISIBLE);
        Button submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setVisibility(View.VISIBLE);
        ++numOfCoffees;
        display(numOfCoffees);
        displayPrice(numOfCoffees * 5);


    }

    public void decrement(View view) {
        TextView priceSummaryTextView = (TextView) findViewById(R.id.price_summary_text_view);
        priceSummaryTextView.setText("PRICE (excluding toppings)");
        Button sendEmailButton = (Button) findViewById(R.id.email);
        sendEmailButton.setVisibility(View.INVISIBLE);
        Button submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setVisibility(View.VISIBLE);
        --numOfCoffees;
        if (numOfCoffees <= 0) {
            numOfCoffees = 0;

        }
        display(numOfCoffees);
        displayPrice(numOfCoffees * 5);


    }


}
