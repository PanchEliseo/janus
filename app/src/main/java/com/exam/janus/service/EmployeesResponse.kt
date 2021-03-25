package com.exam.janus.service

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class EmployeesResponse(@SerializedName("id")
                             val id: Int,
                            @SerializedName("employee_name")
                            val employee_name: String?,
                            @SerializedName("employee_salary")
                            val employee_salary: Int,
                            @SerializedName("employee_age")
                            val employee_age: Int,
                            @SerializedName("profile_image")
                            val profile_image: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(employee_name)
        parcel.writeInt(employee_salary)
        parcel.writeInt(employee_age)
        parcel.writeString(profile_image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EmployeesResponse> {
        override fun createFromParcel(parcel: Parcel): EmployeesResponse {
            return EmployeesResponse(parcel)
        }

        override fun newArray(size: Int): Array<EmployeesResponse?> {
            return arrayOfNulls(size)
        }
    }
}