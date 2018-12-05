 /*
  * Copyright (C) 2017 The Android Open Source Project
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *  	http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */

 package com.example.android.android_me.ui;

 import android.support.v7.app.AppCompatActivity;
 import android.os.Bundle;

 import com.example.android.android_me.R;
 import com.example.android.android_me.data.AndroidImageAssets;

 // This activity will display a custom Android image composed of three body parts: head, body, and legs
 public class MainActivity extends AppCompatActivity {

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

         HeadFragment headFragment = new HeadFragment();
         BodyFragment bodyFragment = new BodyFragment();
         LegFragment legFragment = new LegFragment();


         android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

         fragmentManager.beginTransaction()
                 .add(R.id.head_container, headFragment)
                 .add(R.id.body_container, bodyFragment)
                 .add(R.id.leg_container, legFragment)
                 .commit();

     }
 }
