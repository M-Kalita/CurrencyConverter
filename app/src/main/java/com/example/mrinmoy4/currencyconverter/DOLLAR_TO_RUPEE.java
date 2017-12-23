package com.example.mrinmoy4.currencyconverter;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class DOLLAR_TO_RUPEE extends AppCompatActivity {

    String DOL;
    EditText dolrup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dollar__to__rupee);

        DownloadTask task = new DownloadTask();
        task.execute("http://apilayer.net/api/live?access_key=d0c3b54ca66587f62110fc0072cd0973&currencies=USD,INR&format=1"   );

    }

    public void DtoR (View view){
        dolrup = (EditText)findViewById(R.id.dolrup);
        Double y = Double.parseDouble(dolrup.getText().toString() );
        Double z =  Double.parseDouble(DOL)* y;
        TextView i = (TextView) findViewById(R.id.textView3);
        String stringdouble= Double.toString(z);
        i.setText(" ₹ " + stringdouble );

    }


    public class DownloadTask extends AsyncTask<String,Void,String> {


        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url =new URL(urls[0]);
                urlConnection= (HttpURLConnection) url.openConnection();
                InputStream in= urlConnection.getInputStream();
                InputStreamReader reader=new InputStreamReader(in);
                int data =reader.read();

                while(data!= -1){
                    char current = (char) data;
                    result += current;
                    data= reader.read();
                }
                return result;

            }catch (Exception e){

                Toast.makeText(getApplicationContext(), " Bad Connection ", Toast.LENGTH_LONG);

            }



            return  null;
        }

        protected void onPostExecute(String result){

            super.onPostExecute(result);

            Log.i("PEEPPEEPPPEPPEPPEEPEPPE",result);



                try {
                    JSONObject jsonObject = new JSONObject(result).getJSONObject("quotes");
                    DOL = jsonObject.getString("USDINR");
                    String RUP = jsonObject.getString("USDINR");
                    String message = "";
                    Log.i("DOLLARRRR", jsonObject.getString("USDUSD"));
                    Log.i("RUPPPP", jsonObject.getString("USDINR"));


                    Log.i("JSON", DOL);

                    if (DOL != "") {
                        message += DOL;
                    } else {
                        Toast.makeText(getApplicationContext(), " Bad Connection ", Toast.LENGTH_LONG);
                    }

              /* JSONArray array= new JSONArray("QUOTES");


                for (int i = 0; i<= array.length();i++){
                    JSONObject jsonpart = array.getJSONObject(i);

                    Log.i("DOLLARRRR",jsonpart.getString("USDUSD"));
                    Log.i("RUPEESSS",jsonpart.getString("USDINR"));

                    String DOLLAR="";
                    String RUPEES="";
                    DOLLAR=jsonpart.getString("USDUSD");
                    RUPEES= jsonpart.getString("USDINR");

                    if(DOLLAR !="" && RUPEES!="") {

                        message += DOLLAR + ";" + description + "\r\n";

                    }

                }
                /*if(message!=""){
                    Text2.setText(message);
                }   */
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), " Bad Connection ", Toast.LENGTH_LONG);
                }

        }


    }



}
