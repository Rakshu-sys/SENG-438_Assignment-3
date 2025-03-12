package org.jfree.data.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.*;
import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jfree.data.KeyedValues;
import org.jmock.Expectations;
import org.jmock.Mockery;
import static org.junit.Assert.assertArrayEquals;

/**
 * Unit tests for DataUtilities, validating operations such as column sums, 
 * row sums, array conversions, and cumulative percentage calculations.
 */
public class DataUtilitiesTest {

    private Mockery context;
    private Values2D mockValues;
    private KeyedValues mockKeyedValues;

    @Before
    public void initialize() {
        // Setting up the mock context before each test.
        context = new Mockery();
        mockValues = context.mock(Values2D.class);
        mockKeyedValues = context.mock(KeyedValues.class);
    }

    @After
    public void cleanup() {
        // Resetting references after each test.
        context = null;
        mockValues = null;
        mockKeyedValues = null;
    }

    /**
     * Tests summing a column in an empty table, ensuring result is zero.
     */
    @Test
    public void testEmptyTableColumnSum() {
        context.checking(new Expectations() {{
            allowing(mockValues).getRowCount();
            will(returnValue(0));
        }});

        int columnIndex = 1;
        double result = DataUtilities.calculateColumnTotal(mockValues, columnIndex);
        assertEquals("Expected zero for an empty column", 0.0, result, 0.000000001);
    }

    /**
     * Tests summing a row in an empty table, ensuring result is zero.
     */
    @Test
    public void testEmptyTableRowSum() {
        context.checking(new Expectations() {{
            allowing(mockValues).getColumnCount();
            will(returnValue(0));
            allowing(mockValues).getRowCount();
            will(returnValue(0));
        }});

        int rowIndex = 2;
        double result = DataUtilities.calculateRowTotal(mockValues, rowIndex);
        assertEquals("Expected zero for an empty row", 0.0, result, 0.000000001);
    }

    /**
     * Tests summing a single-column table with one row.
     */
    @Test
    public void testSingleRowColumnSum() {
        context.checking(new Expectations() {{
            allowing(mockValues).getRowCount();
            will(returnValue(1));
            allowing(mockValues).getValue(0, 2);
            will(returnValue(55.3));
        }});

        int columnIndex = 2;
        double result = DataUtilities.calculateColumnTotal(mockValues, columnIndex);
        assertEquals("Expected single value in column to be the sum", 55.3, result, 0.000000001);
    }

    /**
     * Tests summing a row containing mixed positive and negative values.
     */
    @Test
    public void testMixedValuesRowSum() {
        context.checking(new Expectations() {{
            allowing(mockValues).getColumnCount();
            will(returnValue(3));
            
            allowing(mockValues).getValue(1, 0);
            will(returnValue(12.5));
            allowing(mockValues).getValue(1, 1);
            will(returnValue(-4.8));
            allowing(mockValues).getValue(1, 2);
            will(returnValue(7.2));
        }});

        int rowIndex = 1;
        double result = DataUtilities.calculateRowTotal(mockValues, rowIndex);
        assertEquals("Expected row sum of 12.5, -4.8, and 7.2", 14.9, result, 0.000000001);
    }

    /**
     * Tests converting an array of positive values into a Number array.
     */
    @Test
    public void testConvertPositiveArrayToNumbers() {
        Number[] expected = { 4.6, 7.3, 9.8 };
        double[] input = { 4.6, 7.3, 9.8 };
        
        Number[] result = DataUtilities.createNumberArray(input);
        assertArrayEquals("Expected correct conversion to Number array", expected, result);
    }

    /**
     * Tests converting an array of negative values into a Number array.
     */
    @Test
    public void testConvertNegativeArrayToNumbers() {
        Number[] expected = { -3.2, -6.9, -1.4 };
        double[] input = { -3.2, -6.9, -1.4 };
        
        Number[] result = DataUtilities.createNumberArray(input);
        assertArrayEquals("Expected correct conversion for negative values", expected, result);
    }

    /**
     * Tests handling of null input when converting a double array to a Number array.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConvertNullArrayToNumbers() {
        DataUtilities.createNumberArray(null);
    }

    /**
     * Tests conversion of a 2D array of doubles into a 2D Number array.
     */
    @Test
    public void testConvert2DArrayToNumbers() {
        Number[][] expected = {
            { 3.2, 6.1 },
            { 8.4, 9.7 }
        };
        double[][] input = {
            { 3.2, 6.1 },
            { 8.4, 9.7 }
        };

        Number[][] result = DataUtilities.createNumberArray2D(input);
        assertArrayEquals("Expected correct 2D array conversion", expected, result);
    }

    /**
     * Tests handling of null input when converting a 2D double array.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConvertNull2DArrayToNumbers() {
        DataUtilities.createNumberArray2D(null);
    }

    /**
     * Tests calculation of cumulative percentages for a KeyedValues object.
     */
    @Test
    public void testCumulativePercentageCalculation() {
        context.checking(new Expectations() {{
            allowing(mockKeyedValues).getItemCount();
            will(returnValue(3));

            allowing(mockKeyedValues).getKey(0);
            will(returnValue("A"));
            allowing(mockKeyedValues).getKey(1);
            will(returnValue("B"));
            allowing(mockKeyedValues).getKey(2);
            will(returnValue("C"));

            allowing(mockKeyedValues).getValue(0);
            will(returnValue(5.0));
            allowing(mockKeyedValues).getValue(1);
            will(returnValue(10.0));
            allowing(mockKeyedValues).getValue(2);
            will(returnValue(15.0));

            allowing(mockKeyedValues).getKeys();
            will(returnValue(Arrays.asList("A", "B", "C")));
        }});

        KeyedValues result = DataUtilities.getCumulativePercentages(mockKeyedValues);
        assertNotNull("Cumulative percentages should not be null", result);
    }
}