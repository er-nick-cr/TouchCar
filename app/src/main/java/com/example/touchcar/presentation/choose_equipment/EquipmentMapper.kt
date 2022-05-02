package com.example.touchcar.presentation.choose_equipment

import android.content.Context
import com.example.touchcar.R
import com.example.touchcar.domain.entity.Equipment
import com.example.touchcar.domain.entity.Parameter
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class EquipmentMapper @Inject constructor(
    @ApplicationContext
    private val context: Context
) {
    fun mapEquipmentValues(equipments: List<Equipment>): List<Equipment> {
        return equipments.map { equipment ->  equipment.copy(parameters = mapParametersValues(equipment.parameters))}
    }

    private fun mapParametersValues(parameters: List<Parameter>): List<Parameter> {
        return parameters.map { parameter ->
            when {
                parameter.parameterValue.isBlank() -> parameter.copy(parameterValue = context.resources.getString(R.string.equipment_search_parameter_no))
                parameter.parameterValue == "*" -> parameter.copy(parameterValue = context.resources.getString(R.string.equipment_search_parameter_yes))
                else -> parameter
            }
        }
    }
}