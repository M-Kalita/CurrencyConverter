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

import static android.R.attr.id;
import static android.R.attr.x;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.example.mrinmoy4.currencyconverter.R.id.dolrup;

public class RUPEE_TO_DOLLAR extends AppCompatActivity {

    String RUP;
    EditText rupdol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rupee__to__dollar);

        DownloadTask task = new DownloadTask();
        task.execute("http://apilayer.net/api/live?access_key=d0c3b54ca66587f62110fc0072cd0973&currencies=USD,INR&format=1"   );
    }
   /* public void RtoD(View view){
        EditText x = (EditText) findViewById(R.id.enteredRupee);
        Double y = Double.parseDouble(x.getText().toString());
        Double z = 0.015* y;
        TextView i = (TextView) findViewById(R.id.textView6);
        String stringdouble= Double.toString(z);
        i.setText(stringdouble + " $");
    }  */



    public void RtoD (View view){
        rupdol = (EditText)findViewById(R.id.rupdol);
        Double y = Double.parseDouble(rupdol.getText().toString() );
        Double z =  y/Double.parseDouble(RUP);
        TextView i = (TextView) findViewById(R.id.textView5);
        String stringdouble= Double.toString(z);
        i.setText(" $ " + stringdouble  );

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
                JSONObject jsonObject=new JSONObject(result).getJSONObject("quotes");
                RUP = jsonObject.getString("USDINR");
                //  DOLL = parseDouble("DOL");
               // String RUP = jsonObject.getString("USDINR");
                //RUPP = parseDouble("RUP");

                Log.i("DOLLARRRR",jsonObject.getString("USDUSD"));
                Log.i("RUPPPP",jsonObject.getString("USDINR"));


              //  Log.i("JSON",DOL);

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
