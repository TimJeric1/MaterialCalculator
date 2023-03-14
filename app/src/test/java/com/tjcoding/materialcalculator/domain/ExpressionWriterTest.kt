package com.tjcoding.materialcalculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ExpressionWriterTest {

    private lateinit var writer: ExpressionWriter

    @Before
    fun setUp() {
        writer = ExpressionWriter()
    }

    @Test
    fun `Inital parentheses parsed`() {

        writer.processAction(CalculatorAction.Parentheses)
        writer.processAction(CalculatorAction.Number(5))
        writer.processAction(CalculatorAction.Op(Operation.ADD))
        writer.processAction(CalculatorAction.Number(4))
        writer.processAction(CalculatorAction.Parentheses)

        val actual = writer.expression
        val expected = "(5+4)"

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Closing parentheses at the start not parsed`() {

        writer.processAction(CalculatorAction.Parentheses)
        writer.processAction(CalculatorAction.Parentheses)

        val actual = writer.expression
        val expected = "(("

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Parentheses around a number are parser`() {

        writer.processAction(CalculatorAction.Parentheses)
        writer.processAction(CalculatorAction.Number(6))
        writer.processAction(CalculatorAction.Parentheses)

        val actual = writer.expression
        val expected = "(6)"

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Calculate expressio`() {

        writer.processAction(CalculatorAction.Parentheses)
        writer.processAction(CalculatorAction.Number(5))
        writer.processAction(CalculatorAction.Op(Operation.ADD))
        writer.processAction(CalculatorAction.Number(4))
        writer.processAction(CalculatorAction.Parentheses)
        writer.processAction(CalculatorAction.Calculate)

        val actual = writer.expression
        val expected = "9.0"

        assertThat(actual).isEqualTo(expected)
    }


}