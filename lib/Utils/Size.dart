

import 'package:flutter/widgets.dart';

class ScreenSize{



 double getHeight(context){
   double _height=MediaQuery.of(context).size.height;
    return _height;
  }

 double getWidth(context){
   double _height=MediaQuery.of(context).size.width;
   return _height;
 }

 double getText(context){
   double _text=MediaQuery.of(context).textScaleFactor;
   return _text;

 }

}