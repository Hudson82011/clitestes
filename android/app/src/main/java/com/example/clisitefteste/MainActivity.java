package com.example.clisitefteste;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

import static br.com.softwareexpress.sitef.android.CliSiTef.CMD_SHOW_QRCODE_FIELD;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import java.util.HashMap;
import java.util.Objects;

import br.com.itfast.tectoy.CorLed;
import br.com.itfast.tectoy.Dispositivo;
import br.com.itfast.tectoy.TecToy;
import br.com.softwareexpress.sitef.android.CliSiTef;
import br.com.softwareexpress.sitef.android.ICliSiTefListener;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {





    public static TecToy tectoy;

    Bundle bundle = new Bundle();



    //ServiceSat serviceSat;
    Activity activity;
    public static MethodChannel.Result resultFlutter;
    MethodChannel methodChannel;
    private String CHANNEL = "samples.flutter.sammi/Card";
    public static Context mContext;


    // const int CAMPO_COMPROVANTE_CLIENTE=121;
    //  const int CAMPO_COMPROVANTE_ESTAB=122;


    String card="";
    String cardEstab="";

    String tipoCard="";
    String bandCard="";
    String codRede="";
    String redeAdquiq="";
    String auth="";


    String numCard="";
    String cnpj="";
    String nsu="";

    String idVpn="";



    String resultCd="";
    String stageEnd="";



    Handler hndMessage= new Handler();

    CliSiTef clisitef;



    String bufferTemp="Aguarde...";


    ICliSiTefListener cliSiTefListener= new ICliSiTefListener() {
        @Override
        public void onData(int stage, int command, int fieldId, int minLength, int maxLength, byte[] input) {


            // clisit//ef.continueTransaction(clisitef.getBuffer());
            System.out.println("o stage é:"+stage);
            System.out.println("o input é:"+input.toString());
            System.out.println("o fieldid é:"+fieldId);
            System.out.println("o command é:"+command);
            System.out.println("o buffer é:"+clisitef.getBuffer());


            System.out.println(stage);

            if (stage == 1) {
                //  clisitef.continueTransaction(clisitef.getBuffer());


            } else if (stage == 2) {
                // Evento onData recebido em uma finishTransaction
            }



            switch (command) {

                case CliSiTef.CMD_RESULT_DATA:
                    switch (fieldId) {
                        case 2: //121
                            tipoCard=clisitef.getBuffer();
                            break;
                        case 121: //121
                            card=clisitef.getBuffer();
                            break;
                        case 122: //122
                            cardEstab=clisitef.getBuffer();
                            break;
                        //case 133:
                        case 134:
                            nsu=clisitef.getBuffer();
                            break;
                        case 135:
                            auth=clisitef.getBuffer();
                            break;
                        case 156:
                            bandCard=clisitef.getBuffer();
                            break;
                        case 158:
                            codRede=clisitef.getBuffer();
                            break;
                        case 2021:
                            numCard=clisitef.getBuffer();
                            break;


                        //  default:  clisitef.continueTransaction("");
                        //Tratar comando
                    }

                    break;
                case CliSiTef.CMD_SHOW_MSG_CASHIER:
                    bufferTemp=clisitef.getBuffer().equals("")?bufferTemp:clisitef.getBuffer();
                case CliSiTef.CMD_SHOW_MSG_CUSTOMER:
                    bufferTemp=clisitef.getBuffer().equals("")?bufferTemp:clisitef.getBuffer();
                case CliSiTef.CMD_SHOW_MSG_CASHIER_CUSTOMER:
                    bufferTemp=clisitef.getBuffer().equals("")?bufferTemp:clisitef.getBuffer();
                    //Tratar comando
                    break;
                case CliSiTef.CMD_SHOW_MENU_TITLE:
                case CliSiTef.CMD_SHOW_HEADER:
                    //Tratar comando
                    break;
                case CliSiTef.CMD_CLEAR_MSG_CASHIER:
                case CliSiTef.CMD_CLEAR_MSG_CUSTOMER:
                case CliSiTef.CMD_CLEAR_MSG_CASHIER_CUSTOMER:
                case CliSiTef.CMD_CLEAR_MENU_TITLE:
                case CliSiTef.CMD_CLEAR_HEADER:
                    bufferTemp=clisitef.getBuffer().equals("")?bufferTemp:clisitef.getBuffer();
                    clisitef.continueTransaction("0");

                    //Tratar comando

                    break;
                case CliSiTef.CMD_CONFIRM_GO_BACK:
                case CliSiTef.CMD_CONFIRMATION: {
                    bufferTemp=clisitef.getBuffer().equals("")?bufferTemp:clisitef.getBuffer();
                    clisitef.continueTransaction("0");

                    return;
                }
                case CliSiTef.CMD_GET_FIELD_CURRENCY:
                    clisitef.continueTransaction("1");
                case CliSiTef.CMD_GET_FIELD_BARCODE:
                case CliSiTef.CMD_GET_FIELD: {
                    //  if(bufferTemp.equals("Registro Comnect")){
                    clisitef.continueTransaction(idVpn);
                    //  }

                    //Tratar comando
                    return ;
                }
                case CliSiTef.CMD_GET_MENU_OPTION: {
                    //Tratar comando menu
                    bufferTemp=clisitef.getBuffer().equals("")?bufferTemp:clisitef.getBuffer();
                    clisitef.continueTransaction("1");
                    return;
                }
                case CliSiTef.CMD_PRESS_ANY_KEY: {
                    bufferTemp=clisitef.getBuffer().equals("")?bufferTemp:clisitef.getBuffer();
                    clisitef.continueTransaction("1");
                    return;
                }
                case CliSiTef.CMD_ABORT_REQUEST:
                    break;
                case CMD_SHOW_QRCODE_FIELD:
                    //Tratar comando
                    break;
                case CliSiTef.CMD_REMOVE_QRCODE_FIELD:
                    //Tratar comando
                    break;
                case 950:
                    cnpj=clisitef.getBuffer().equals("")?bufferTemp:clisitef.getBuffer();
                default:
                    break;
            }
            clisitef.continueTransaction("");


        }

        @Override
        public void onTransactionResult(int stage, int resultCode) {
            System.out.println("o stage é"+stage);
            System.out.println("o resultcode é"+resultCode);
            int trnResultCode = resultCode;
            if (stage == 1 && resultCode == 0) { // Confirm the transaction
                try {



                    resultCd=String.valueOf(trnResultCode);

                    stageEnd=String.valueOf(stage);


                    //clisitef.finishTransaction(1);
                } catch (Exception e) {

                    //Tratar Exception
                }
            } else {
                if (resultCode == 0) {




                    //Transação ok e pode exibir comprovante
                } else {

                    //Finaliza aplicação
                }
            }
        }

    };



    public void getHandler(){


        clisitef = new CliSiTef (this.getApplicationContext ());
        clisitef.setMessageHandler(hndMessage);






    }


    public void initPin(String ip,String empresa,String terminal,String msg,String vpn){


        // clisitef = new CliSiTef (this.getApplicationContext ());
        idVpn=vpn;

        System.out.println(empresa);
        System.out.println(terminal);
        System.out.println(msg);
        System.out.println(vpn);

        try {
            // clisitef.setDebug(true);
            //clisitef.configure(ip, empresa, terminal,
            //  "[TipoPinPad=ANDROID_USB;TipoComunicacaoExterna=COMNECT;]");

            //clisitef.setDebug(true);
            clisitef.configure(ip, "00000000", terminal,
                    "[TipoPinPad=ANDROID_USB;]");

        }catch (Exception err){

            System.out.println(err.toString());

        }

        clisitef.pinpad.setDisplayMessage(msg,true);






    }









    public int iniciaTran(int modalidade,String valor,String docFiscal,String dataFiscal,String horaFiscal,String operador,String restricoes ){


        System.out.println("Inicializando a função");

        bufferTemp="";
        card="";
        cardEstab="";
        numCard="";
        cnpj="";
        nsu="";
        resultCd="";
        stageEnd="";

        tipoCard="";
        bandCard="";
        codRede="";
        redeAdquiq="";
        auth="";


        int sts = clisitef.startTransaction (cliSiTefListener, modalidade, valor, docFiscal,
                dataFiscal, horaFiscal, operador, restricoes);





        return sts;


    }

    /* */


    private final int REQUEST_PERMISSION_PHONE_STATE=1;
    // @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        getHandler();
        GeneratedPluginRegistrant.registerWith(flutterEngine);


        activity = this;
        mContext = this;
        tectoy = new TecToy(Dispositivo.K2_MINI, getApplicationContext());



        if (SDK_INT >= Build.VERSION_CODES.R) {
            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s",getApplicationContext().getPackageName())));
                startActivityForResult(intent, 2296);
            } catch (Exception e) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivityForResult(intent, 2296);
            }
        } else {
            //below android 11
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{WRITE_EXTERNAL_STORAGE}, 1);
        }



        BinaryMessenger binaryMessenger = flutterEngine.getDartExecutor().getBinaryMessenger();
        methodChannel = new MethodChannel(binaryMessenger, CHANNEL);








        methodChannel.setMethodCallHandler((call, result) -> {
            bundle = new Bundle();
            resultFlutter = result;

            if (call.method.equals("card")) {
                // HashMap map = call.argument("args");
                //formatActionPrinter(map);

                HashMap map = call.argument("args");
                call.argument("args");

                iniciaTran(Integer.parseInt(map.get("modal").toString()),
                        map.get("val").toString(),
                        map.get("doc").toString(),
                        map.get("data").toString(),
                        map.get("hora").toString(),
                        map.get("op").toString(),
                        "");
            }else if(call.method.equals("buffer")){

                HashMap<String, String> val = new HashMap<String, String>();
                val.put("buffer", bufferTemp);
                val.put("numCard",numCard);
                val.put("cnpj",cnpj);
                val.put("nsu",nsu);
                val.put("resultCd",resultCd);
                val.put("stageEnd",stageEnd);
                val.put("tipoCard",tipoCard);
                val.put("bandCard",bandCard);
                val.put("codRede",codRede);
                val.put("redeAdquiq",redeAdquiq);
                val.put("auth",auth);
                val.put("card", card);
                val.put("cardEstab", cardEstab);
                result.success(val);

            }else if(call.method.equals("abort")){
                clisitef.abortTransaction(1);

                try {
                    clisitef.finishTransaction(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }else if(call.method.equals("start")){

                HashMap map = call.argument("args");
                call.argument("args");

                initPin(
                        map.get("ip").toString(),
                        map.get("empresa").toString(),
                        map.get("terminal").toString(),
                        map.get("msg").toString(),
                        map.get("vpn").toString()

                );
            }else if(call.method.equals("finish")){
                HashMap map = call.argument("args");
                call.argument("args");

                //clisitef.finishTransaction(cliSiTefListener,Integer.parseInt(String.valueOf(map.get("val"))),"","","","");

                try {
                    clisitef.finishTransaction(Integer.parseInt(String.valueOf(map.get("val"))));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else if(call.method.equals("finishEsp")){
                HashMap map = call.argument("args");
                call.argument("args");

                clisitef.finishTransaction(cliSiTefListener,Integer.parseInt(String.valueOf(map.get("val"))),String.valueOf(map.get("val")),"","","");




            }else if(call.method.equals("led")){

                HashMap map = call.argument("args");

                assert map != null;
                System.out.println(map.get("color"));

                String color= (String) map.get("color");

                if(Objects.equals(color, "VERDE")){
                    tectoy.ligarLedStatus(CorLed.VERDE);
                }else if(Objects.equals(color, "VERMELHO")){
                    tectoy.ligarLedStatus(CorLed.VERMELHO);
                }else if(Objects.equals(color, "AZUL")){
                    tectoy.ligarLedStatus(CorLed.AZUL);
                }else{
                    tectoy.desligarLedStatus();

                }

            }else if(call.method.equals("imp")){

                HashMap map = call.argument("args");
                byte[] imp= (byte[]) map.get("impBody");
                tectoy.imprimir(    imp);

            }
        });
    }







}
