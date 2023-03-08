package com.example.materialcalculator.domain

import org.junit.Test
import com.google.common.truth.Truth.assertThat


class ExpressionParserTest {

    private lateinit var parser: ExpressionParser


    @Test
    fun `Simple expression is properly parsed`() {
        parser = ExpressionParser("3+5-3*4/3")

        val actual = parser.parse()

        val expected = listOf(
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(5.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(3.0),
        )

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Expression with parentheses is properly parsed`() {
        parser = ExpressionParser("4-(4*5)")

        val actual = parser.parse()
        val expected = listOf(
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Parentheses(ParenthesesType.Opening),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(5.0),
            ExpressionPart.Parentheses(ParenthesesType.Closing),
        )

        assertThat(actual).isEqualTo(expected)
    }

}