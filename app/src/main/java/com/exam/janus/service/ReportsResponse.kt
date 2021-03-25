package com.exam.janus.service

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ReportsResponse(@SerializedName("status")
                           val status: String?,
                           @SerializedName("data")
                           val data: MutableList<EmployeesResponse>,
                           @SerializedName("message")
                           val message: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            TODO("data"),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(status)
        parcel.writeString(message)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ReportsResponse> {
        override fun createFromParcel(parcel: Parcel): ReportsResponse {
            return ReportsResponse(parcel)
        }

        override fun newArray(size: Int): Array<ReportsResponse?> {
            return arrayOfNulls(size)
        }
    }
}