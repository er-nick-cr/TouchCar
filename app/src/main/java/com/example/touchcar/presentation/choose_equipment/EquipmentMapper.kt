package com.example.touchcar.presentation.choose_equipment

import android.content.Context
import com.example.touchcar.R
import com.example.touchcar.domain.entity.Parameter
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class EquipmentMapper @Inject constructor(
    @ApplicationContext
    val context: Context
) {

    fun mapParametersValues(parameters: List<Parameter>): List<Parameter> {
        parameters.map { parameter ->
            if (parameter.parameterValue.isBlank()) {
                parameter.parameterValue = context.resources.getString(R.string.equipment_search_parameter_no)
            } else if (parameter.parameterValue == "*") {
                parameter.parameterValue =  context.resources.getString(R.string.equipment_search_parameter_yes)
            }
        }
        return parameters
    }
}