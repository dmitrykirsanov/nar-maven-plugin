package com.github.maven_nar.cpptasks.trolltech;

import java.io.CharArrayReader;
import java.io.IOException;

import com.github.maven_nar.cpptasks.trolltech.MetaObjectParser;

import junit.framework.TestCase;

/**
 * Tests for the MetaObjectParser class.
 */
public final class TestMetaObjectParser
    extends TestCase {
  /**
   * Constructor.
   * @param name String test name
   */
  public TestMetaObjectParser(final String name) {
    super(name);
  }

  /**
   * Test that the presence of Q_OBJECT causes hasQObject to return true.
   * @throws IOException test fails on IOException
   */
  public void testHasQObject1() throws IOException {
    CharArrayReader reader = new CharArrayReader(
        "    Q_OBJECT  ".toCharArray());
    boolean hasQObject = MetaObjectParser.hasQObject(reader);
    assertTrue(hasQObject);
  }

  /**
   * Test that the lack of Q_OBJECT causes hasQObject to return false.
   * @throws IOException test fails on IOException
   */
  public void testHasQObject2() throws IOException {
    CharArrayReader reader = new CharArrayReader(
        "    Q_OBJ ECT  ".toCharArray());
    boolean hasQObject = MetaObjectParser.hasQObject(reader);
    assertFalse(hasQObject);
  }

}
