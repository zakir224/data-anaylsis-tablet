package com.nhf.task;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.nhf.app.Login;
import com.nhf.app.PatientRecord;
import com.nhf.icu.R;
import com.nhf.io.IOUtil;
import com.nhf.parameters.BaseForm;
import com.nhf.parameters.MonitoringParams;
import com.nhf.parameters.Ventilator;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by ZAKIR_224 on 2/22/14.
 */
public class IcuParameters {

    public static final String INSERT_PARAM_URL = "";
    public static final String GET_PARAM_URL = "";
    public static final String UPDATE_PARAM_URL = "";
    //public static final String GET_URL = "http://www.bishwalab.org/index.php/welcome/";
    public static final String GET_URL = "http://10.0.3.2/ccu1/index.php/welcome/";


    public static void initializeParams(JSONArray jsonArray){
        try {
            PatientRecord patientRecord = PatientRecord.getInstance();
            Log.d("icu_load","Initializing PatientRecord");
            patientRecord.setEtco2(jsonArray.getJSONObject(0).getDouble("etco2")); /*Set the value of all fields of all parameters like this*/
            patientRecord.setTimeId(jsonArray.getJSONObject(0).getInt("time_id"));
            patientRecord.setSpo2(jsonArray.getJSONObject(0).getInt("spo2"));
            patientRecord.setRr(jsonArray.getJSONObject(0).getDouble("rr"));
            patientRecord.setHr_mt(jsonArray.getJSONObject(0).getInt("hr_mt"));
            patientRecord.setRhytm(jsonArray.getJSONObject(0).getInt("rhytm"));
            patientRecord.setS_abp(jsonArray.getJSONObject(0).getInt("s_abp"));
            patientRecord.setD_abp(jsonArray.getJSONObject(0).getInt("d_abp"));
            patientRecord.setM_abp(jsonArray.getJSONObject(0).getInt("m_abp"));
            patientRecord.setS_nibp(jsonArray.getJSONObject(0).getInt("s_nibp"));
            patientRecord.setD_nibp(jsonArray.getJSONObject(0).getInt("d_nibp"));
            patientRecord.setM_nibp(jsonArray.getJSONObject(0).getInt("m_nibp"));
            patientRecord.setP_temp(jsonArray.getJSONObject(0).getDouble("p_temp"));
            patientRecord.setS_temp(jsonArray.getJSONObject(0).getDouble("s_temp"));
            patientRecord.setC_temp(jsonArray.getJSONObject(0).getDouble("c_temp"));
            patientRecord.setCvp(jsonArray.getJSONObject(0).getInt("cvp"));
            patientRecord.setCvp_text(jsonArray.getJSONObject(0).getString("cvp_text"));
            patientRecord.setLa(jsonArray.getJSONObject(0).getInt("la"));
            patientRecord.setPap(jsonArray.getJSONObject(0).getInt("pap"));
            patientRecord.setPeri(jsonArray.getJSONObject(0).getInt("peri"));
            patientRecord.setPpr(jsonArray.getJSONObject(0).getInt("ppr"));
            patientRecord.setPpl(jsonArray.getJSONObject(0).getInt("ppl"));
            patientRecord.setCo(jsonArray.getJSONObject(0).getDouble("co"));
            patientRecord.setCi(jsonArray.getJSONObject(0).getDouble("ci"));
            patientRecord.setSvr(jsonArray.getJSONObject(0).getString("svr"));
            patientRecord.setPvr(jsonArray.getJSONObject(0).getString("pvr"));
            patientRecord.setEvents(jsonArray.getJSONObject(0).getString("events"));
            patientRecord.setPatient_id(jsonArray.getJSONObject(0).getInt("patient_id"));
            patientRecord.setDoc_id(jsonArray.getJSONObject(0).getInt("doc_id"));
            patientRecord.setComments(jsonArray.getJSONObject(0).getString("comments"));
            patientRecord.setMode(jsonArray.getJSONObject(0).getString("mode"));
            patientRecord.setMode_text(jsonArray.getJSONObject(0).getString("mode_text"));
            patientRecord.setRate(jsonArray.getJSONObject(0).getInt("rate"));
            patientRecord.setRsbi(jsonArray.getJSONObject(0).getInt("rsbi"));
            patientRecord.setPip(jsonArray.getJSONObject(0).getInt("pip"));
            patientRecord.setTv(jsonArray.getJSONObject(0).getInt("tv"));
            patientRecord.setPeep(jsonArray.getJSONObject(0).getInt("peep"));
            patientRecord.setMap(jsonArray.getJSONObject(0).getInt("map"));
            patientRecord.setTi(jsonArray.getJSONObject(0).getDouble("ti"));
            patientRecord.setI(jsonArray.getJSONObject(0).getInt("i"));
            patientRecord.setE(jsonArray.getJSONObject(0).getDouble("e"));
            patientRecord.setPsupp(jsonArray.getJSONObject(0).getInt("psupp"));
            patientRecord.setFio2(jsonArray.getJSONObject(0).getDouble("fio2"));
            patientRecord.setVent_comment(jsonArray.getJSONObject(0).getString("vent_comment"));
            patientRecord.setDoctor_name(jsonArray.getJSONObject(0).getString("doctor_name"));
            patientRecord.setType(jsonArray.getJSONObject(0).getInt("type"));
            patientRecord.setPh(jsonArray.getJSONObject(0).getDouble("ph"));
            patientRecord.setPco2(jsonArray.getJSONObject(0).getDouble("pco2"));
            patientRecord.setPo2(jsonArray.getJSONObject(0).getDouble("po2"));
            patientRecord.setHco3(jsonArray.getJSONObject(0).getDouble("hco3"));
            patientRecord.setSat(jsonArray.getJSONObject(0).getDouble("sat"));
            patientRecord.setBe(jsonArray.getJSONObject(0).getDouble("be"));
            patientRecord.setHb(jsonArray.getJSONObject(0).getDouble("hb"));
            patientRecord.setNa(jsonArray.getJSONObject(0).getDouble("na"));
            patientRecord.setK(jsonArray.getJSONObject(0).getDouble("k"));
            patientRecord.setCl(jsonArray.getJSONObject(0).getDouble("cl"));
            patientRecord.setAnion_gp(jsonArray.getJSONObject(0).getDouble("anion_gp"));
            patientRecord.setAado2(jsonArray.getJSONObject(0).getDouble("aado2"));
            patientRecord.setLact(jsonArray.getJSONObject(0).getDouble("lact"));
            patientRecord.setCa(jsonArray.getJSONObject(0).getDouble("ca"));
            patientRecord.setGlu(jsonArray.getJSONObject(0).getDouble("glu"));
            patientRecord.setAmtUrine(jsonArray.getJSONObject(0).getInt("amt_urine"));
            patientRecord.setTotalUrine(jsonArray.getJSONObject(0).getInt("total_urine"));
            patientRecord.setRt_pl(jsonArray.getJSONObject(0).getInt("rt_pl"));
            patientRecord.setLt_pl(jsonArray.getJSONObject(0).getInt("lt_pl"));
            patientRecord.setMed(jsonArray.getJSONObject(0).getInt("med"));
            patientRecord.setPeric(jsonArray.getJSONObject(0).getInt("peric"));
            patientRecord.setHr_t(jsonArray.getJSONObject(0).getInt("hr_t"));
            patientRecord.setG_t(jsonArray.getJSONObject(0).getInt("g_t"));
            patientRecord.setAmt_gastric(jsonArray.getJSONObject(0).getInt("amt_gastric"));
            patientRecord.setTotalGastric(jsonArray.getJSONObject(0).getInt("total_gastric"));
            patientRecord.setAmt_lab(jsonArray.getJSONObject(0).getInt("amt_lab"));
            patientRecord.setTotalLab(jsonArray.getJSONObject(0).getInt("total_lab"));
            patientRecord.setTotal_out(jsonArray.getJSONObject(0).getInt("total_out"));
            patientRecord.setOutput_comment(jsonArray.getJSONObject(0).getString("output_comment"));
            patientRecord.setTransfus(jsonArray.getJSONObject(0).getInt("transfus"));
            patientRecord.setInfuss(jsonArray.getJSONObject(0).getInt("infuss"));
            patientRecord.setB_s(jsonArray.getJSONObject(0).getInt("b_s"));
            patientRecord.setN_s(jsonArray.getJSONObject(0).getInt("n_s"));
            patientRecord.setTotal4(jsonArray.getJSONObject(0).getDouble("total4"));
            patientRecord.setAlbumin(jsonArray.getJSONObject(0).getDouble("albumin"));
            patientRecord.setDoctor_name3(jsonArray.getJSONObject(0).getString("doctor_name3"));
            patientRecord.setDial1(jsonArray.getJSONObject(0).getDouble("dial1"));
            patientRecord.setDial2(jsonArray.getJSONObject(0).getDouble("dial2"));
            patientRecord.setDial3(jsonArray.getJSONObject(0).getDouble("dial3"));
            patientRecord.setDial4(jsonArray.getJSONObject(0).getDouble("dial4"));
            patientRecord.setDial5(jsonArray.getJSONObject(0).getDouble("dial5"));
            patientRecord.setDial6(jsonArray.getJSONObject(0).getDouble("dial6"));
            patientRecord.setDose1(jsonArray.getJSONObject(0).getDouble("dose1"));
            patientRecord.setDose2(jsonArray.getJSONObject(0).getDouble("dose2"));
            patientRecord.setDose3(jsonArray.getJSONObject(0).getDouble("dose3"));
            patientRecord.setDose4(jsonArray.getJSONObject(0).getDouble("dose4"));
            patientRecord.setDose5(jsonArray.getJSONObject(0).getDouble("dose5"));
            patientRecord.setDose6(jsonArray.getJSONObject(0).getDouble("dose6"));
            patientRecord.setDialUnit1(jsonArray.getJSONObject(0).getInt("dial_unit_1"));
            patientRecord.setDialUnit2(jsonArray.getJSONObject(0).getInt("dial_unit_2"));
            patientRecord.setDialUnit3(jsonArray.getJSONObject(0).getInt("dial_unit_3"));
            patientRecord.setDialUnit4(jsonArray.getJSONObject(0).getInt("dial_unit_4"));
            patientRecord.setDialUnit5(jsonArray.getJSONObject(0).getInt("dial_unit_5"));
            patientRecord.setDialUnit6(jsonArray.getJSONObject(0).getInt("dial_unit_6"));
            patientRecord.setDrugAmount1(jsonArray.getJSONObject(0).getDouble("drug_amount_1"));
            patientRecord.setDrugAmount2(jsonArray.getJSONObject(0).getDouble("drug_amount_2"));
            patientRecord.setDrugAmount3(jsonArray.getJSONObject(0).getDouble("drug_amount_3"));
            patientRecord.setDrugAmount4(jsonArray.getJSONObject(0).getDouble("drug_amount_4"));
            patientRecord.setDrugAmount5(jsonArray.getJSONObject(0).getDouble("drug_amount_5"));
            patientRecord.setDrugAmount6(jsonArray.getJSONObject(0).getDouble("drug_amount_6"));
            patientRecord.setSaline1(jsonArray.getJSONObject(0).getDouble("saline_1"));
            patientRecord.setSaline2(jsonArray.getJSONObject(0).getDouble("saline_2"));
            patientRecord.setSaline3(jsonArray.getJSONObject(0).getDouble("saline_3"));
            patientRecord.setSaline4(jsonArray.getJSONObject(0).getDouble("saline_4"));
            patientRecord.setSaline5(jsonArray.getJSONObject(0).getDouble("saline_5"));
            patientRecord.setSaline6(jsonArray.getJSONObject(0).getDouble("saline_6"));

            Log.d("icu_load","Initializing PatientRecord completed");
            Log.d("icu_load",patientRecord.getTime_picked()+" "+patientRecord.getTimeId()+" "+patientRecord.getPod());
        } catch (Exception e) {
            Log.d("PatientRecordException",e.getMessage());
        }
    }

    public static ArrayList<NameValuePair> getParameters(final Context context){
        ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
       try {
           Calendar today = Calendar.getInstance();
           PatientRecord patientRecord = PatientRecord.getInstance();

           String am_pm;
           if (today.get(Calendar.AM_PM) == 1) {
               am_pm = " pm";
           } else {
               am_pm = " am";
           }

           Log.d("update","UpdateParameters setParameters()");
           postParameters.add(new BasicNameValuePair("time_updated", today.get(Calendar.HOUR) + ":" + today.get(Calendar.MINUTE) + am_pm));
           postParameters.add(new BasicNameValuePair("time_picked", patientRecord.getTime_picked()));
           postParameters.add(new BasicNameValuePair("time_id",String.valueOf(patientRecord.getTimeId())));
           postParameters.add(new BasicNameValuePair("patient_id", String.valueOf(patientRecord.getPatient_id())));
           postParameters.add(new BasicNameValuePair("pod", patientRecord.getPod()));
           postParameters.add(new BasicNameValuePair("spo2", String.valueOf(patientRecord.getSpo2())));
           postParameters.add(new BasicNameValuePair("etco2", String.valueOf(patientRecord.getEtco2())));
           postParameters.add(new BasicNameValuePair("hr_mt", String.valueOf(patientRecord.getHr_mt())));
           postParameters.add(new BasicNameValuePair("rhytm", String.valueOf(patientRecord.getRhytm())));
           postParameters.add(new BasicNameValuePair("peri", String.valueOf(patientRecord.getPeri())));
           postParameters.add(new BasicNameValuePair("s_abp", String.valueOf(patientRecord.getS_abp())));
           postParameters.add(new BasicNameValuePair("d_abp", String.valueOf(patientRecord.getD_abp())));
           postParameters.add(new BasicNameValuePair("m_abp", String.valueOf(patientRecord.getM_abp())));
           postParameters.add(new BasicNameValuePair("s_nibp", String.valueOf(patientRecord.getS_nibp())));
           postParameters.add(new BasicNameValuePair("pvr", String.valueOf(patientRecord.getPvr())));
           postParameters.add(new BasicNameValuePair("d_nibp", String.valueOf(patientRecord.getD_nibp())));
           postParameters.add(new BasicNameValuePair("m_nibp", String.valueOf(patientRecord.getM_nibp())));
           postParameters.add(new BasicNameValuePair("p_temp", String.valueOf(patientRecord.getP_temp())));
           postParameters.add(new BasicNameValuePair("s_temp", String.valueOf(patientRecord.getS_temp())));
           postParameters.add(new BasicNameValuePair("c_temp", String.valueOf(patientRecord.getC_temp())));
           postParameters.add(new BasicNameValuePair("cvp", String.valueOf(patientRecord.getCvp())));
           postParameters.add(new BasicNameValuePair("cvp_text", String.valueOf(patientRecord.getCvp_text())));
           postParameters.add(new BasicNameValuePair("svr", String.valueOf(patientRecord.getSvr())));
           postParameters.add(new BasicNameValuePair("la", String.valueOf(patientRecord.getLa())));
           postParameters.add(new BasicNameValuePair("pap", String.valueOf(patientRecord.getPap())));
           postParameters.add(new BasicNameValuePair("rr", String.valueOf(patientRecord.getRr())));
           postParameters.add(new BasicNameValuePair("co", String.valueOf(patientRecord.getCo())));
           postParameters.add(new BasicNameValuePair("ppr", String.valueOf(patientRecord.getPpr())));
           postParameters.add(new BasicNameValuePair("ppl", String.valueOf(patientRecord.getPpl())));
           postParameters.add(new BasicNameValuePair("rhytm", String.valueOf(patientRecord.getRhytm())));
           postParameters.add(new BasicNameValuePair("peri", String.valueOf(patientRecord.getPeri())));
           postParameters.add(new BasicNameValuePair("ci", String.valueOf(patientRecord.getCi())));
           postParameters.add(new BasicNameValuePair("events", String.valueOf(patientRecord.getEvents())));
           postParameters.add(new BasicNameValuePair("comments", String.valueOf(patientRecord.getComments())));
           postParameters.add(new BasicNameValuePair("type", String.valueOf(patientRecord.getType())));
           postParameters.add(new BasicNameValuePair("ph", String.valueOf(patientRecord.getPh())));
           postParameters.add(new BasicNameValuePair("pco2", String.valueOf(patientRecord.getPco2())));
           postParameters.add(new BasicNameValuePair("po2", String.valueOf(patientRecord.getPo2())));
           postParameters.add(new BasicNameValuePair("hco3", String.valueOf(patientRecord.getHco3())));
           postParameters.add(new BasicNameValuePair("sat", String.valueOf(patientRecord.getSat())));
           postParameters.add(new BasicNameValuePair("be", String.valueOf(patientRecord.getBe())));
           postParameters.add(new BasicNameValuePair("hb", String.valueOf(patientRecord.getHb())));
           postParameters.add(new BasicNameValuePair("na", String.valueOf(patientRecord.getNa())));
           postParameters.add(new BasicNameValuePair("k", String.valueOf(patientRecord.getK())));
           postParameters.add(new BasicNameValuePair("cl", String.valueOf(patientRecord.getCl())));
           postParameters.add(new BasicNameValuePair("anion_gp", String.valueOf(patientRecord.getAnion_gp())));
           postParameters.add(new BasicNameValuePair("aado2", IOUtil.getString(patientRecord.getAado2())));
           postParameters.add(new BasicNameValuePair("lact", String.valueOf(patientRecord.getLact())));
           postParameters.add(new BasicNameValuePair("ca", String.valueOf(patientRecord.getCa())));
           postParameters.add(new BasicNameValuePair("glu", String.valueOf(patientRecord.getGlu())));
           postParameters.add(new BasicNameValuePair("mode", String.valueOf(patientRecord.getMode())));
           postParameters.add(new BasicNameValuePair("rate", String.valueOf(patientRecord.getRate())));
           postParameters.add(new BasicNameValuePair("rsbi", String.valueOf(patientRecord.getRsbi())));
           postParameters.add(new BasicNameValuePair("pip", String.valueOf(patientRecord.getPip())));
           postParameters.add(new BasicNameValuePair("tv", String.valueOf(patientRecord.getTv())));
           postParameters.add(new BasicNameValuePair("peep", String.valueOf(patientRecord.getPeep())));
           postParameters.add(new BasicNameValuePair("map", String.valueOf(patientRecord.getMap())));
           postParameters.add(new BasicNameValuePair("ti", String.valueOf(patientRecord.getTi())));
           postParameters.add(new BasicNameValuePair("psupp", String.valueOf(patientRecord.getPsupp())));
           postParameters.add(new BasicNameValuePair("fio2", String.valueOf(patientRecord.getFio2())));
           postParameters.add(new BasicNameValuePair("i", String.valueOf(patientRecord.getI())));
           postParameters.add(new BasicNameValuePair("e", String.valueOf(patientRecord.getE())));
           postParameters.add(new BasicNameValuePair("mode_text", String.valueOf(patientRecord.getMode_text())));
           postParameters.add(new BasicNameValuePair("vent_comment", String.valueOf(patientRecord.getVent_comment())));
           postParameters.add(new BasicNameValuePair("amt_urine", String.valueOf(patientRecord.getAmtUrine())));
           //postParameters.add(new BasicNameValuePair("total_urine", String.valueOf(patientRecord.getTotalUrine())));
           postParameters.add(new BasicNameValuePair("rt_pl", String.valueOf(patientRecord.getRt_pl())));
           postParameters.add(new BasicNameValuePair("lt_pl", String.valueOf(patientRecord.getLt_pl())));
           postParameters.add(new BasicNameValuePair("med", String.valueOf(patientRecord.getMed())));
           postParameters.add(new BasicNameValuePair("peric", String.valueOf(patientRecord.getPeric())));
           postParameters.add(new BasicNameValuePair("hr_t",String.valueOf(patientRecord.getHr_t())));
           //postParameters.add(new BasicNameValuePair("g_t", String.valueOf(patientRecord.getG_t())));
           postParameters.add(new BasicNameValuePair("amt_gastric", String.valueOf(patientRecord.getAmt_gastric())));
           //postParameters.add(new BasicNameValuePair("total_gastric", String.valueOf(patientRecord.getTotalGastric())));
           postParameters.add(new BasicNameValuePair("amt_lab", String.valueOf(patientRecord.getAmt_lab())));
           //postParameters.add(new BasicNameValuePair("total_lab", String.valueOf(patientRecord.getTotalLab())));
           //postParameters.add(new BasicNameValuePair("total_out", String.valueOf(patientRecord.getTotal_out())));
           postParameters.add(new BasicNameValuePair("output_comment", String.valueOf(patientRecord.getOutput_comment())));
           postParameters.add(new BasicNameValuePair("transfus", String.valueOf(patientRecord.getTransfus())));
           postParameters.add(new BasicNameValuePair("infuss", String.valueOf(patientRecord.getInfuss())));
           postParameters.add(new BasicNameValuePair("b_s", String.valueOf(patientRecord.getB_s())));
           postParameters.add(new BasicNameValuePair("n_s", String.valueOf(patientRecord.getN_s())));
           postParameters.add(new BasicNameValuePair("albumin", String.valueOf(patientRecord.getAlbumin())));
           postParameters.add(new BasicNameValuePair("total4",String.valueOf(patientRecord.getTotal4())));
           postParameters.add(new BasicNameValuePair("doctor_name3", patientRecord.getDoctor_name3()));
           postParameters.add(new BasicNameValuePair("dial1",String.valueOf(patientRecord.getDial1())));
           postParameters.add(new BasicNameValuePair("dial2",String.valueOf(patientRecord.getDial2())));
           postParameters.add(new BasicNameValuePair("dial3",String.valueOf(patientRecord.getDial3())));
           postParameters.add(new BasicNameValuePair("dial4",String.valueOf(patientRecord.getDial4())));
           postParameters.add(new BasicNameValuePair("dial5",String.valueOf(patientRecord.getDial5())));
           postParameters.add(new BasicNameValuePair("dial6",String.valueOf(patientRecord.getDial6())));
           postParameters.add(new BasicNameValuePair("dose1",String.valueOf(patientRecord.getDose1())));
           postParameters.add(new BasicNameValuePair("dose2",String.valueOf(patientRecord.getDose2())));
           postParameters.add(new BasicNameValuePair("dose3",String.valueOf(patientRecord.getDose3())));
           postParameters.add(new BasicNameValuePair("dose4",String.valueOf(patientRecord.getDose4())));
           postParameters.add(new BasicNameValuePair("dose5",String.valueOf(patientRecord.getDose5())));
           postParameters.add(new BasicNameValuePair("dose6",String.valueOf(patientRecord.getDose6())));
           postParameters.add(new BasicNameValuePair("dial_unit_1",String.valueOf(patientRecord.getDialUnit1())));
           postParameters.add(new BasicNameValuePair("dial_unit_2",String.valueOf(patientRecord.getDialUnit2())));
           postParameters.add(new BasicNameValuePair("dial_unit_3",String.valueOf(patientRecord.getDialUnit3())));
           postParameters.add(new BasicNameValuePair("dial_unit_4",String.valueOf(patientRecord.getDialUnit4())));
           postParameters.add(new BasicNameValuePair("dial_unit_5",String.valueOf(patientRecord.getDialUnit5())));
           postParameters.add(new BasicNameValuePair("dial_unit_6",String.valueOf(patientRecord.getDialUnit6())));
           postParameters.add(new BasicNameValuePair("drug_amount_1",String.valueOf(patientRecord.getDrugAmount1())));
           postParameters.add(new BasicNameValuePair("drug_amount_2",String.valueOf(patientRecord.getDrugAmount2())));
           postParameters.add(new BasicNameValuePair("drug_amount_3",String.valueOf(patientRecord.getDrugAmount3())));
           postParameters.add(new BasicNameValuePair("drug_amount_4",String.valueOf(patientRecord.getDrugAmount4())));
           postParameters.add(new BasicNameValuePair("drug_amount_5",String.valueOf(patientRecord.getDrugAmount5())));
           postParameters.add(new BasicNameValuePair("drug_amount_6",String.valueOf(patientRecord.getDrugAmount6())));


           Log.d("update",patientRecord.getTime_picked()+" "+
                   patientRecord.getTimeId()+" "+patientRecord.getPod()+" "+patientRecord.getAmtUrine()+" "+patientRecord.getDoctor_name3());

       }catch (Exception e){
           new AlertDialog.Builder(context)
                   .setTitle("Internet connection error")
                   .setMessage("Connect to the internet and try again")
                   .setNeutralButton("Close",
                           new DialogInterface.OnClickListener() {
                               public void onClick(DialogInterface dlg,
                                                   int sumthin) {

                               }
                           }).show();
       }
        return postParameters;
    }

    public static int getTimeId(String timePicked, String[] stringArray){

        int timeId = 0;
        for(int i = 0 ; i < stringArray.length ;i++) {
            if(stringArray[i].equals(timePicked))
            {
                timeId = i;
                Log.d("time",i+"");
            }

        }
        return timeId;
    }

}