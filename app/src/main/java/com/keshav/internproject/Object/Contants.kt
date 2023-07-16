package com.keshav.internproject.Object

import com.keshav.internproject.Model.ContantModel
import com.keshav.internproject.R

object Contants {
      // We defined object becoause it do not required any instance it is like singleton class
        fun getContantsDetails() : ArrayList<ContantModel>{
            val list = ArrayList<ContantModel>()
            val list1 = ContantModel(
                R.drawable.amico
                ,"About Us"
            )
            val list2 = ContantModel(
                R.drawable.pana
                ,"Our Mission"
            )
            val list3 = ContantModel(
                R.drawable.start3
                ,"Our Vision"
            )
            list.add(list1)
            list.add(list2)
            list.add(list3)
            return list

        }
}