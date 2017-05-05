package com.project.nfcid;


import com.project.nfcid.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
 
public class MainActivity extends Activity{
 
	Button btnReceiveNFCID;
	Button btnSendNFCID;
    Button btnViewProducts;
    Button btnNewProduct;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        
        
        // Buttons
        btnReceiveNFCID = (Button) findViewById(R.id.btnReceiveNFCID);
        btnSendNFCID = (Button) findViewById(R.id.btnSendNFCID);
        btnViewProducts = (Button) findViewById(R.id.btnViewProducts);
        btnNewProduct = (Button) findViewById(R.id.btnCreateProduct);
 
        // view NFCID click event
        btnReceiveNFCID.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View view) {
                // Launching All products Activity
                Intent i = new Intent(getApplicationContext(), GetNFCIDActivity.class);
                startActivity(i);
 
            }
        });
        
        btnSendNFCID.setOnClickListener(new View.OnClickListener() {
        	 
            @Override
            public void onClick(View view) {
                // Launching All products Activity
                Intent i = new Intent(getApplicationContext(), SendNFCIDActivity.class);
                startActivity(i);
 
            }
        });
        
        // view products click event
        btnViewProducts.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View view) {
            	if(!GetNFCIDActivity.getWebID().toString().contains(".0.")){
           		 Toast.makeText(MainActivity.this,"You do not have a valid ID", 
           		          Toast.LENGTH_SHORT).show();
            	}
            	else{
            		Intent i = new Intent(getApplicationContext(), AllProductsActivity.class);
            		startActivity(i);
            	}
            }
        });
 
        // view products click event
        btnNewProduct.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View view) {
                // Launching create new product activity
            	if(!GetNFCIDActivity.getWebID().toString().contains(".0.")){
            		 Toast.makeText(MainActivity.this,"You do not have a valid ID", 
            		          Toast.LENGTH_SHORT).show();
            	}
            	else{
            		Intent i = new Intent(getApplicationContext(), NewProductActivity.class);
            		startActivity(i);
            	}
 
            }
        });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        	
        	Toast.makeText(this, "Version 4.0", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    
}
