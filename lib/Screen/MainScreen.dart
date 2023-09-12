import 'package:clisitefteste/Utils/Size.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:intl/intl.dart';
import 'package:random_string/random_string.dart';


class MainScreen extends StatefulWidget {
  const MainScreen({super.key});

  @override
  State<MainScreen> createState() => _MainScreenState();
}

class _MainScreenState extends State<MainScreen> {


  ScreenSize _size=ScreenSize();
  final _platform = const MethodChannel("samples.flutter.sammi/Card");




  void _startPinPad(){
    Map<String, dynamic> _args = Map();
    _args["ip"] ="192.168.10.245";
    //_args["empresa"] ="00000000";
    _args["terminal"] = "S0000007";
    _args["msg"] = "Bem vindo";
    _args["vpn"]="";

    print(_args);

    _platform.invokeMapMethod("start", {"args": _args});
    print('chamei');
  }



  void initState(){
    _startPinPad();


    super.initState();
  }



  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(),
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Container(width:_size.getWidth(context),),
          ElevatedButton(
              onPressed: () {
                Map<String, dynamic> _args = Map();
                _args["modal"] = 03;
                _args["val"] = "12,50";
                _args["doc"] =randomNumeric(6).toString();
                _args["data"] = DateFormat("yyyy/MM/dd")
                    .format(DateTime.now())
                    .toString()
                    .replaceAll("/", "");
                _args["hora"] = DateFormat("HH:mm:ss")
                    .format(DateTime.now())
                    .toString()
                    .replaceAll(":", "");

                print(_args['hora']);

                _args["op"] = "4";

                _platform.invokeMapMethod("card", {"args": _args});

              },
              style: ButtonStyle(
                  backgroundColor: MaterialStateProperty.all(
                      Color.fromRGBO(185, 79, 79, 1)),shape:MaterialStateProperty.all<RoundedRectangleBorder>(
                  RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(18.0),
                  )
              )),
              child: Container(
                  height: 120,
                  width: 320,
                  child: Center(
                      child: Row(
                        children: [
                          Container(
                            width: 80,
                            height: 40,
                            child: Icon(Icons.credit_card,size: 60,color: Colors.white,),
                          ),
                          Text(
                            "CRÉDITO",
                            style: TextStyle(fontSize: 38,color: Colors.white),
                          ),
                        ],
                      )))),
          Padding(padding: EdgeInsets.only(top: _size.getHeight(context)*0.1)),
          ElevatedButton(
              onPressed: () {
                Map<String, dynamic> _args = Map();
                _args["modal"] = 04;
                _args["val"] = "12,50";
                _args["doc"] =randomNumeric(6).toString();
                _args["data"] = DateFormat("yyyy/MM/dd")
                    .format(DateTime.now())
                    .toString()
                    .replaceAll("/", "");
                _args["hora"] = DateFormat("HH:mm:ss")
                    .format(DateTime.now())
                    .toString()
                    .replaceAll(":", "");

                print(_args['hora']);

                _args["op"] = "4";

                _platform.invokeMapMethod("card", {"args": _args});

              },
              style: ButtonStyle(
                  backgroundColor: MaterialStateProperty.all(
                      Color.fromRGBO(45, 12, 162, 1)),shape:MaterialStateProperty.all<RoundedRectangleBorder>(
                  RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(18.0),
                  )
              )),
              child: Container(
                  height: 120,
                  width: 320,
                  child: Center(
                      child: Row(
                        children: [
                          Container(
                            width: 80,
                            height: 40,
                            child: Icon(Icons.credit_card,size: 60,color: Colors.white,),
                          ),
                          Text(
                            "DEBITO",
                            style: TextStyle(fontSize: 38,color: Colors.white),
                          ),
                        ],
                      )))),
        ],
      ),
    );
  }
}
