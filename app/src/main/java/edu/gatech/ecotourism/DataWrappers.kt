package edu.gatech.ecotourism

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Listing(val title: String, val description: String) : Parcelable

@Parcelize
class Contact(val name: String, val phone: String, val email: String, val picture: Int) : Parcelable