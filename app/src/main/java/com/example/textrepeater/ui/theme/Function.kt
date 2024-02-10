package com.example.textrepeater.ui.theme

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

fun Output(inputText: String, newLine : Boolean, Period : Boolean, count : Int,isChecked : Boolean): String {
    var output : String = ""
    if(inputText.isEmpty()){
        return ""
    }
    else{
        if(isChecked){
            if(newLine && !Period){
                for(i in 1..count){
                    output += "$i) $inputText\n"
                }
                return output
            }
            else if(!newLine && Period){
                for(i in 1..count){
                    output += "$i) $inputText. "
                }
                return output
            }
            else if(newLine && Period){
                for(i in 1..count){
                    output += "$i) $inputText. \n"
                }
                return output
            }
            else {
                for(i in 1..count){
                    output += "$i) $inputText"
                }
                return output
            }
        }
        else
        {
            if(newLine && !Period){
                for(i in 1..count){
                    output += "$inputText\n"
                }
                return output
            }
            else if(!newLine && Period){
                for(i in 1..count){
                    output += "$inputText. "
                }
                return output
            }
            else if(newLine && Period){
                for(i in 1..count){
                    output += "$inputText. \n"
                }
                return output
            }
            else {
                for(i in 1..count){
                    output += "$inputText"
                }
                return output
            }
        }
    }
}
