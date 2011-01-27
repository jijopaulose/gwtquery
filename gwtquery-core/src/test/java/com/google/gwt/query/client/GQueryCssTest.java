/*
 * Copyright 2011, The gwtquery team.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.query.client;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Float;
import com.google.gwt.dom.client.Style.FontStyle;
import com.google.gwt.dom.client.Style.ListStyleType;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Style.VerticalAlign;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.query.client.css.CSS;
import com.google.gwt.query.client.css.ImageValue;
import com.google.gwt.query.client.css.Length;
import com.google.gwt.query.client.css.RGBColor;
import com.google.gwt.query.client.css.BackgroundAttachmentProperty.BackgroundAttachment;
import com.google.gwt.query.client.css.BackgroundPositionProperty.BackgroundPosition;
import com.google.gwt.query.client.css.BackgroundRepeatProperty.BackgroundRepeat;
import com.google.gwt.query.client.css.BorderStyleProperty.LineStyle;
import com.google.gwt.query.client.css.BorderWidthProperty.LineWidth;
import com.google.gwt.query.client.css.FontSizeProperty.FontSize;
import com.google.gwt.query.client.css.FontVariantProperty.FontVariant;
import com.google.gwt.query.client.css.ListStylePositionProperty.ListStylePosition;
import com.google.gwt.query.client.css.TextAlignProperty.TextAlign;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Test class for testing css part.
 */
public class GQueryCssTest extends GWTTestCase {

  static Element e = null;

  static HTML testPanel = null;

  public String getModuleName() {
    return "com.google.gwt.query.Query";
  }

  public void gwtSetUp() {
    if (e == null) {
      testPanel = new HTML();
      RootPanel.get().add(testPanel);
      e = testPanel.getElement();
      e.setId("core-tst");
    } else {
      e.setInnerHTML("");
    }
  }

  public void testBackgroundAttachmentProperty() {

    $(e).html("<div id='test'>Content</div>");

    $("#test").css(CSS.BACKGROUND_ATTACHMENT, BackgroundAttachment.FIXED);

    assertEquals("fixed", $("#test").css("backgroundAttachment"));
    assertEquals("fixed", $("#test").css(CSS.BACKGROUND_ATTACHMENT));

    $("#test").css(CSS.BACKGROUND_ATTACHMENT, BackgroundAttachment.SCROLL);

    assertEquals("scroll", $("#test").css("backgroundAttachment"));
    assertEquals("scroll", $("#test").css(CSS.BACKGROUND_ATTACHMENT));

  }

  public void testBackgroundColorProperty() {

    $(e).html("<div id='test'>Content</div>");

    $("#test").css(CSS.BACKGROUND_COLOR, RGBColor.AQUA);

    assertEquals("aqua", $("#test").css("backgroundColor"));
    assertEquals("aqua", $("#test").css(CSS.BACKGROUND_COLOR));

    $("#test").css(CSS.BACKGROUND_COLOR, RGBColor.rgb("#112233"));

    assertEquals("#112233", $("#test").css("backgroundColor"));
    assertEquals("#112233", $("#test").css(CSS.BACKGROUND_COLOR));

    $("#test").css(CSS.BACKGROUND_COLOR, RGBColor.rgb(35, 45, 55));
    assertEquals("rgb(35,45,55)", $("#test").css("backgroundColor"));
    assertEquals("rgb(35,45,55)", $("#test").css(CSS.BACKGROUND_COLOR));

  }

  public void testBackgroundImageProperty() {

    $(e).html("<div id='test'>Content</div>");

    $("#test").css(CSS.BACKGROUND_IMAGE, ImageValue.url("image.jpg"));

    assertEquals("url('image.jpg')", $("#test").css("backgroundImage"));
    assertEquals("url('image.jpg')", $("#test").css(CSS.BACKGROUND_IMAGE));
  }

  public void testBackgroundPositionProperty() {

    $(e).html("<div id='test'>Content</div>");

    $("#test").css(CSS.BACKGROUND_POSITION, BackgroundPosition.CENTER);
    assertEquals("center", $("#test").css("backgroundPosition"));
    assertEquals("center", $("#test").css(CSS.BACKGROUND_POSITION));

    $("#test").css(CSS.BACKGROUND_POSITION, BackgroundPosition.CENTER_CENTER);
    assertEquals("center center", $("#test").css(CSS.BACKGROUND_POSITION));

    $("#test").css(CSS.BACKGROUND_POSITION, BackgroundPosition.CENTER_TOP);
    assertEquals("center top", $("#test").css(CSS.BACKGROUND_POSITION));

    $("#test").css(CSS.BACKGROUND_POSITION, BackgroundPosition.CENTER_BOTTOM);
    assertEquals("center bottom", $("#test").css(CSS.BACKGROUND_POSITION));

    $("#test").css(CSS.BACKGROUND_POSITION, BackgroundPosition.LEFT);
    assertEquals("left", $("#test").css(CSS.BACKGROUND_POSITION));

    $("#test").css(CSS.BACKGROUND_POSITION, BackgroundPosition.LEFT_TOP);
    assertEquals("left top", $("#test").css(CSS.BACKGROUND_POSITION));

    $("#test").css(CSS.BACKGROUND_POSITION, BackgroundPosition.LEFT_CENTER);
    assertEquals("left center", $("#test").css(CSS.BACKGROUND_POSITION));

    $("#test").css(CSS.BACKGROUND_POSITION, BackgroundPosition.LEFT_BOTTOM);
    assertEquals("left bottom", $("#test").css(CSS.BACKGROUND_POSITION));

    $("#test").css(CSS.BACKGROUND_POSITION, BackgroundPosition.RIGHT);
    assertEquals("right", $("#test").css(CSS.BACKGROUND_POSITION));

    $("#test").css(CSS.BACKGROUND_POSITION, BackgroundPosition.RIGHT_TOP);
    assertEquals("right top", $("#test").css(CSS.BACKGROUND_POSITION));

    $("#test").css(CSS.BACKGROUND_POSITION, BackgroundPosition.RIGHT_CENTER);
    assertEquals("right center", $("#test").css(CSS.BACKGROUND_POSITION));

    $("#test").css(CSS.BACKGROUND_POSITION, BackgroundPosition.RIGHT_BOTTOM);
    assertEquals("right bottom", $("#test").css(CSS.BACKGROUND_POSITION));

    $("#test").css(CSS.BACKGROUND_POSITION,
        BackgroundPosition.position(12, 12, Unit.PX));
    assertEquals("12px 12px", $("#test").css(CSS.BACKGROUND_POSITION));

    $("#test").css(CSS.BACKGROUND_POSITION,
        BackgroundPosition.position(12, 12, Unit.PCT));
    assertEquals("12% 12%", $("#test").css(CSS.BACKGROUND_POSITION));

  }

  public void testBackgroundProperty() {

    $(e).html("<div id='test'>Content</div>");

    $("#test").css(CSS.BACKGROUND, RGBColor.TRANSPARENT,
        ImageValue.url("back.jpg"), BackgroundRepeat.NO_REPEAT,
        BackgroundAttachment.SCROLL, BackgroundPosition.CENTER);
    assertEquals("transparent url('back.jpg') no-repeat scroll center", $(
        "#test").css("background"));
    assertEquals("transparent url('back.jpg') no-repeat scroll center", $(
        "#test").css(CSS.BACKGROUND));

  }

  public void testBackgroundRepeatProperty() {

    $(e).html("<div id='test'>Content</div>");

    $("#test").css(CSS.BACKGROUND_REPEAT, BackgroundRepeat.REPEAT_X);

    assertEquals("repeat-x", $("#test").css("backgroundRepeat"));
    assertEquals("repeat-x", $("#test").css(CSS.BACKGROUND_REPEAT));
  }

  public void testBorderColorProperty() {

    $(e).html("<div id='test'>Content</div>");

    $("#test").css(CSS.BORDER_COLOR, RGBColor.AQUA);
    assertEquals("aqua", $("#test").css("borderColor"));
    assertEquals("aqua", $("#test").css(CSS.BORDER_COLOR));

    $("#test").css(CSS.BORDER_BOTTOM_COLOR, RGBColor.BLACK);
    assertEquals("black", $("#test").css("borderBottomColor"));
    assertEquals("black", $("#test").css(CSS.BORDER_BOTTOM_COLOR));

    $("#test").css(CSS.BORDER_TOP_COLOR, RGBColor.FUSCHIA);
    assertEquals("fuschia", $("#test").css("borderTopColor"));
    assertEquals("fuschia", $("#test").css(CSS.BORDER_TOP_COLOR));

    $("#test").css(CSS.BORDER_LEFT_COLOR, RGBColor.GRAY);
    assertEquals("gray", $("#test").css("borderLeftColor"));
    assertEquals("gray", $("#test").css(CSS.BORDER_LEFT_COLOR));

    $("#test").css(CSS.BORDER_RIGHT_COLOR, RGBColor.WHITE);
    assertEquals("white", $("#test").css("borderRightColor"));
    assertEquals("white", $("#test").css(CSS.BORDER_RIGHT_COLOR));

  }

  public void testBorderProperty() {

    $(e).html("<div id='test'>Content</div>");

    $("#test").css(CSS.BORDER, LineWidth.THICK, LineStyle.DASHED,
        RGBColor.BLACK);
    assertEquals("thick dashed black", $("#test").css("border"));
    assertEquals("thick dashed black", $("#test").css(CSS.BORDER));

    $("#test").css(CSS.BORDER, LineWidth.length(15, Unit.PX),
        LineStyle.SOLID, RGBColor.rgb("#000000"));
    assertEquals("15px solid #000000", $("#test").css("border"));
    assertEquals("15px solid #000000", $("#test").css(CSS.BORDER));

  }

  public void testBorderStyleProperty() {

    $(e).html("<div id='test'>Content</div>");

    $("#test").css(CSS.BORDER_STYLE, LineStyle.DOTTED);
    assertEquals("dotted", $("#test").css("borderStyle"));
    assertEquals("dotted", $("#test").css(CSS.BORDER_STYLE));

    $("#test").css(CSS.BORDER_BOTTOM_STYLE, LineStyle.DASHED);
    assertEquals("dashed", $("#test").css("borderBottomStyle"));
    assertEquals("dashed", $("#test").css(CSS.BORDER_BOTTOM_STYLE));

    $("#test").css(CSS.BORDER_TOP_STYLE, LineStyle.DOUBLE);
    assertEquals("double", $("#test").css("borderTopStyle"));
    assertEquals("double", $("#test").css(CSS.BORDER_TOP_STYLE));

    $("#test").css(CSS.BORDER_LEFT_STYLE, LineStyle.HIDDEN);
    assertEquals("hidden", $("#test").css("borderLeftStyle"));
    assertEquals("hidden", $("#test").css(CSS.BORDER_LEFT_STYLE));

    $("#test").css(CSS.BORDER_RIGHT_STYLE, LineStyle.SOLID);
    assertEquals("solid", $("#test").css("borderRightStyle"));
    assertEquals("solid", $("#test").css(CSS.BORDER_RIGHT_STYLE));

  }

  public void testBorderWidthProperty() {

    $(e).html("<div id='test'>Content</div>");

    $("#test").css(CSS.BORDER_WIDTH, LineWidth.MEDIUM);
    assertEquals("medium", $("#test").css("borderWidth"));
    assertEquals("medium", $("#test").css(CSS.BORDER_WIDTH));

    $("#test").css(CSS.BORDER_WIDTH, Length.px(15));
    assertEquals("15px", $("#test").css(CSS.BORDER_WIDTH));

    $("#test").css(CSS.BORDER_WIDTH, LineWidth.length(Length.px(20)));
    assertEquals("20px", $("#test").css(CSS.BORDER_WIDTH));

    $("#test").css(CSS.BORDER_WIDTH, LineWidth.length(20, Unit.MM));
    assertEquals("20mm", $("#test").css(CSS.BORDER_WIDTH));

    $("#test").css(CSS.BORDER_BOTTOM_WIDTH, LineWidth.THICK);
    assertEquals("thick", $("#test").css("borderBottomWidth"));
    assertEquals("thick", $("#test").css(CSS.BORDER_BOTTOM_WIDTH));

    $("#test").css(CSS.BORDER_TOP_WIDTH, LineWidth.THIN);
    assertEquals("thin", $("#test").css("borderTopWidth"));
    assertEquals("thin", $("#test").css(CSS.BORDER_TOP_WIDTH));

    $("#test").css(CSS.BORDER_LEFT_WIDTH, LineWidth.THIN);
    assertEquals("thin", $("#test").css("borderLeftWidth"));
    assertEquals("thin", $("#test").css(CSS.BORDER_LEFT_WIDTH));

    $("#test").css(CSS.BORDER_RIGHT_WIDTH, LineWidth.THICK);
    assertEquals("thick", $("#test").css("borderRightWidth"));
    assertEquals("thick", $("#test").css(CSS.BORDER_RIGHT_WIDTH));

  }

  public void testColorProperty() {

    $(e).html("<div id='test'>Content</div>");

    $("#test").css(CSS.COLOR, RGBColor.AQUA);

    assertEquals("aqua", $("#test").css("color"));
    assertEquals("aqua", $("#test").css(CSS.COLOR));

    $("#test").css(CSS.COLOR, RGBColor.rgb("#112233"));

    assertEquals("#112233", $("#test").css("color"));
    assertEquals("#112233", $("#test").css(CSS.COLOR));

    $("#test").css(CSS.COLOR, RGBColor.rgb(35, 45, 55));
    assertEquals("rgb(35,45,55)", $("#test").css("color"));
    assertEquals("rgb(35,45,55)", $("#test").css(CSS.COLOR));

  }

  public void testColorValue() {

    $(e).html("<div id='test'>Content</div>");

    $("#test").css(CSS.COLOR, RGBColor.AQUA);
    assertEquals("aqua", $("#test").css(CSS.COLOR));

    $("#test").css(CSS.COLOR, RGBColor.BLACK);
    assertEquals("black", $("#test").css(CSS.COLOR));

    $("#test").css(CSS.COLOR, RGBColor.FUSCHIA);
    assertEquals("fuschia", $("#test").css(CSS.COLOR));

    $("#test").css(CSS.COLOR, RGBColor.GRAY);
    assertEquals("gray", $("#test").css(CSS.COLOR));

    $("#test").css(CSS.COLOR, RGBColor.GREEN);
    assertEquals("green", $("#test").css(CSS.COLOR));

    $("#test").css(CSS.COLOR, RGBColor.INHERIT);
    assertEquals("inherit", $("#test").css(CSS.COLOR));

    $("#test").css(CSS.COLOR, RGBColor.LIME);
    assertEquals("lime", $("#test").css(CSS.COLOR));

    $("#test").css(CSS.COLOR, RGBColor.MAROON);
    assertEquals("maroon", $("#test").css(CSS.COLOR));

    $("#test").css(CSS.COLOR, RGBColor.NAVY);
    assertEquals("navy", $("#test").css(CSS.COLOR));

    $("#test").css(CSS.COLOR, RGBColor.OLIVE);
    assertEquals("olive", $("#test").css(CSS.COLOR));

    $("#test").css(CSS.COLOR, RGBColor.ORANGE);
    assertEquals("orange", $("#test").css(CSS.COLOR));

    $("#test").css(CSS.COLOR, RGBColor.PURPLE);
    assertEquals("purple", $("#test").css(CSS.COLOR));

    $("#test").css(CSS.COLOR, RGBColor.RED);
    assertEquals("red", $("#test").css(CSS.COLOR));

    $("#test").css(CSS.COLOR, RGBColor.SILVER);
    assertEquals("silver", $("#test").css(CSS.COLOR));

    $("#test").css(CSS.COLOR, RGBColor.TEAL);
    assertEquals("teal", $("#test").css(CSS.COLOR));

    $("#test").css(CSS.COLOR, RGBColor.TRANSPARENT);
    assertEquals("transparent", $("#test").css(CSS.COLOR));

    $("#test").css(CSS.COLOR, RGBColor.WHITE);
    assertEquals("white", $("#test").css(CSS.COLOR));

    $("#test").css(CSS.COLOR, RGBColor.YELLOW);
    assertEquals("yellow", $("#test").css(CSS.COLOR));

    $("#test").css(CSS.COLOR, RGBColor.rgb("#112233"));
    assertEquals("#112233", $("#test").css(CSS.COLOR));

    $("#test").css(CSS.COLOR, RGBColor.rgb(35, 45, 55));
    assertEquals("rgb(35,45,55)", $("#test").css(CSS.COLOR));

  }

  public void testCursorProperty() {

    $(e).html("<div id='test'>Content</div>");

    $("#test").css(CSS.CURSOR, Cursor.WAIT);

    assertEquals("wait", $("#test").css("cursor"));
    assertEquals("wait", $("#test").css(CSS.CURSOR));

  }

  public void testDisplayProperty() {

    $(e).html("<div id='test'>Content</div>");

    $("#test").css(CSS.DISPLAY, Display.INLINE);

    assertEquals("inline", $("#test").css("display"));
    assertEquals("inline", $("#test").css(CSS.DISPLAY));

  }

  public void testFloatProperty() {

    $(e).html("<div><div id='test'>Content</div></div>");

    $("#test").css(CSS.FLOAT, Float.LEFT);

    assertEquals("left", $("#test").css("float"));
    assertEquals("left", $("#test").css(CSS.FLOAT));

  }

  public void testFontSizeProperty() {

    $(e).html("<div id='test'>Content</div>");

    $("#test").css(CSS.FONT_SIZE, FontSize.LARGER);
    assertEquals("larger", $("#test").css("fontSize"));
    assertEquals("larger", $("#test").css(CSS.FONT_SIZE));
    
    $("#test").css(CSS.FONT_SIZE, FontSize.LARGE);
    assertEquals("large", $("#test").css(CSS.FONT_SIZE));
    
    $("#test").css(CSS.FONT_SIZE, FontSize.MEDIUM);
    assertEquals("medium", $("#test").css(CSS.FONT_SIZE));
    
    $("#test").css(CSS.FONT_SIZE, FontSize.SMALL);
    assertEquals("small", $("#test").css(CSS.FONT_SIZE));
    
    $("#test").css(CSS.FONT_SIZE, FontSize.SMALLER);
    assertEquals("smaller", $("#test").css(CSS.FONT_SIZE));
    
    $("#test").css(CSS.FONT_SIZE, FontSize.X_LARGE);
    assertEquals("x-large", $("#test").css(CSS.FONT_SIZE));
    
    $("#test").css(CSS.FONT_SIZE, FontSize.X_SMALL);
    assertEquals("x-small", $("#test").css(CSS.FONT_SIZE));
    
    $("#test").css(CSS.FONT_SIZE, FontSize.XX_LARGE);
    assertEquals("xx-large", $("#test").css(CSS.FONT_SIZE));

    $("#test").css(CSS.FONT_SIZE, FontSize.XX_SMALL);
    assertEquals("xx-small", $("#test").css(CSS.FONT_SIZE));
    
    $("#test").css(CSS.FONT_SIZE, Length.px(16));
    assertEquals("16px", $("#test").css(CSS.FONT_SIZE));
  }

  public void testFontStyleProperty() {

    $(e).html("<div id='test'>Content</div>");

    $("#test").css(CSS.FONT_STYLE, FontStyle.ITALIC);
    assertEquals("italic", $("#test").css("fontStyle"));
    assertEquals("italic", $("#test").css(CSS.FONT_STYLE));

  }

  public void testFontVariantProperty() {

    $(e).html("<div id='test'>Content</div>");

    $("#test").css(CSS.FONT_VARIANT, FontVariant.SMALL_CAPS);
    assertEquals("small-caps", $("#test").css("fontVariant"));
    assertEquals("small-caps", $("#test").css(CSS.FONT_VARIANT));

  }

  public void testHeightProperty() {

    $(e).html("<div id='test'>Content</div>");

    $("#test").css(CSS.HEIGHT, Length.px(10));

    assertEquals("10px", $("#test").css("height"));
    assertEquals("10px", $("#test").css(CSS.HEIGHT));
  }

  public void testLengthValue() {

    $(e).html("<div id='test'>Content</div>");

    $("#test").css(CSS.HEIGHT, Length.px(10));
    assertEquals("10px", $("#test").css(CSS.HEIGHT));

    $("#test").css(CSS.HEIGHT, Length.cm(10));
    assertEquals("10cm", $("#test").css(CSS.HEIGHT));

    $("#test").css(CSS.HEIGHT, Length.em(10));
    assertEquals("10em", $("#test").css(CSS.HEIGHT));

    $("#test").css(CSS.HEIGHT, Length.ex(10));
    assertEquals("10ex", $("#test").css(CSS.HEIGHT));

    $("#test").css(CSS.HEIGHT, Length.in(10));
    assertEquals("10in", $("#test").css(CSS.HEIGHT));

    $("#test").css(CSS.HEIGHT, Length.mm(10));
    assertEquals("10mm", $("#test").css(CSS.HEIGHT));

    $("#test").css(CSS.HEIGHT, Length.pc(10));
    assertEquals("10pc", $("#test").css(CSS.HEIGHT));

    $("#test").css(CSS.HEIGHT, Length.pct(10));
    assertEquals("10%", $("#test").css(CSS.HEIGHT));

    $("#test").css(CSS.HEIGHT, Length.pt(10));
    assertEquals("10pt", $("#test").css(CSS.HEIGHT));

  }

  public void testListStyleImageProperty() {

    $(e).html("<ul id='test'><li>Content</li></ul>");

    $("#test").css(CSS.LIST_STYLE_IMAGE, ImageValue.url("arrow.jpg"));
    assertEquals("url('arrow.jpg')", $("#test").css("listStyleImage"));
    assertEquals("url('arrow.jpg')", $("#test").css(CSS.LIST_STYLE_IMAGE));

  }

  public void testListStylePositionProperty() {

    $(e).html("<ul id='test'><li>Content</li></ul>");

    $("#test").css(CSS.LIST_STYLE_POSITION, ListStylePosition.INSIDE);
    assertEquals("inside", $("#test").css("listStylePosition"));
    assertEquals("inside", $("#test").css(CSS.LIST_STYLE_POSITION));
    
    $("#test").css(CSS.LIST_STYLE_POSITION, ListStylePosition.OUTSIDE);
    assertEquals("outside", $("#test").css("listStylePosition"));
    assertEquals("outside", $("#test").css(CSS.LIST_STYLE_POSITION));

  }

  public void testListStyleTypeProperty() {

    $(e).html("<ul id='test'><li>Content</li></ul>");

    $("#test").css(CSS.LIST_STYLE_TYPE, ListStyleType.DISC);
    assertEquals("disc", $("#test").css("listStyleType"));
    assertEquals("disc", $("#test").css(CSS.LIST_STYLE_TYPE));

  }
  
  public void testListStyleProperty() {

    $(e).html("<ul id='test'><li>Content</li></ul>");

    $("#test").css(CSS.LIST_STYLE, ListStyleType.DISC, ListStylePosition.OUTSIDE, ImageValue.NONE);
    assertEquals("disc outside none", $("#test").css("listStyle"));
    assertEquals("disc outside none", $("#test").css(CSS.LIST_STYLE));

    $("#test").css(CSS.LIST_STYLE, ListStyleType.DISC, null, ImageValue.NONE);
    assertEquals("disc none", $("#test").css("listStyle"));
    assertEquals("disc none", $("#test").css(CSS.LIST_STYLE));

    $("#test").css(CSS.LIST_STYLE, null, ListStylePosition.OUTSIDE, ImageValue.NONE);
    assertEquals("outside none", $("#test").css("listStyle"));
    assertEquals("outside none", $("#test").css(CSS.LIST_STYLE));

  }
  
  public void testMarginProperty() {

    $(e).html("<div id='test'>Content</div>");

    $("#test").css(CSS.MARGIN, Length.px(10));

    assertEquals("10px", $("#test").css("margin"));
    assertEquals("10px", $("#test").css(CSS.MARGIN));

    $("#test").css(CSS.MARGIN_TOP, Length.px(20));

    assertEquals("20px", $("#test").css("marginTop"));
    assertEquals("20px", $("#test").css(CSS.MARGIN_TOP));

    $("#test").css(CSS.MARGIN_BOTTOM, Length.px(30));

    assertEquals("30px", $("#test").css("marginBottom"));
    assertEquals("30px", $("#test").css(CSS.MARGIN_BOTTOM));

    $("#test").css(CSS.MARGIN_LEFT, Length.px(40));

    assertEquals("40px", $("#test").css("marginLeft"));
    assertEquals("40px", $("#test").css(CSS.MARGIN_LEFT));

    $("#test").css(CSS.MARGIN_RIGHT, Length.px(50));

    assertEquals("50px", $("#test").css("marginRight"));
    assertEquals("50px", $("#test").css(CSS.MARGIN_RIGHT));
  }
  
  public void testOutlineProperty(){
    $(e).html("<div id='test'>Content</div>");
    
    $("#test").css(CSS.OUTLINE_WIDTH, Length.px(10));
    assertEquals("10px", $("#test").css("outlineWidth"));
    assertEquals("10px", $("#test").css(CSS.OUTLINE_WIDTH));
    
    $("#test").css(CSS.OUTLINE_WIDTH, LineWidth.MEDIUM);
    assertEquals("medium", $("#test").css("outlineWidth"));
    assertEquals("medium", $("#test").css(CSS.OUTLINE_WIDTH));
    
    $("#test").css(CSS.OUTLINE_COLOR, RGBColor.GRAY);
    assertEquals("gray", $("#test").css("outlineColor"));
    assertEquals("gray", $("#test").css(CSS.OUTLINE_COLOR));
    
    $("#test").css(CSS.OUTLINE_STYLE, LineStyle.DOTTED);
    assertEquals("dotted", $("#test").css("outlineStyle"));
    assertEquals("dotted", $("#test").css(CSS.OUTLINE_STYLE));

    $("#test").css(CSS.OUTLINE, RGBColor.BLACK, LineStyle.DASHED, LineWidth.length(15, Unit.PX));
    assertEquals("black dashed 15px", $("#test").css("outline"));
    assertEquals("black dashed 15px", $("#test").css(CSS.OUTLINE));
    
  }
  
  
  public void testPaddingProperty() {

    $(e).html("<div id='test'>Content</div>");

    $("#test").css(CSS.PADDING, Length.px(10));

    assertEquals("10px", $("#test").css("padding"));
    assertEquals("10px", $("#test").css(CSS.PADDING));

    $("#test").css(CSS.PADDING_TOP, Length.px(20));

    assertEquals("20px", $("#test").css("paddingTop"));
    assertEquals("20px", $("#test").css(CSS.PADDING_TOP));

    $("#test").css(CSS.PADDING_BOTTOM, Length.px(30));

    assertEquals("30px", $("#test").css("paddingBottom"));
    assertEquals("30px", $("#test").css(CSS.PADDING_BOTTOM));

    $("#test").css(CSS.PADDING_LEFT, Length.px(40));

    assertEquals("40px", $("#test").css("paddingLeft"));
    assertEquals("40px", $("#test").css(CSS.PADDING_LEFT));

    $("#test").css(CSS.PADDING_RIGHT, Length.px(50));

    assertEquals("50px", $("#test").css("paddingRight"));
    assertEquals("50px", $("#test").css(CSS.PADDING_RIGHT));
  }
  
  public void testTextAlignProperty() {

    $(e).html("<div id='test'>Content</div>");

    $("#test").css(CSS.TEXT_ALIGN, TextAlign.CENTER);
    assertEquals("center", $("#test").css("textAlign"));
    assertEquals("center", $("#test").css(CSS.TEXT_ALIGN));

    $("#test").css(CSS.TEXT_ALIGN, TextAlign.JUSTIFY);
    assertEquals("justify", $("#test").css("textAlign"));
    assertEquals("justify", $("#test").css(CSS.TEXT_ALIGN));

    $("#test").css(CSS.TEXT_ALIGN, TextAlign.LEFT);
    assertEquals("left", $("#test").css("textAlign"));
    assertEquals("left", $("#test").css(CSS.TEXT_ALIGN));

    $("#test").css(CSS.TEXT_ALIGN, TextAlign.RIGHT);
    assertEquals("right", $("#test").css("textAlign"));
    assertEquals("right", $("#test").css(CSS.TEXT_ALIGN));

  }
  
  public void testVerticalAlignProperty() {

    $(e).html("<div id='test'>Content</div>");

    $("#test").css(CSS.VERTICAL_ALIGN, VerticalAlign.BASELINE);
    assertEquals("baseline", $("#test").css("verticalAlign"));
    assertEquals("baseline", $("#test").css(CSS.VERTICAL_ALIGN));

    $("#test").css(CSS.VERTICAL_ALIGN, VerticalAlign.BOTTOM);
    assertEquals("bottom", $("#test").css("verticalAlign"));
    assertEquals("bottom", $("#test").css(CSS.VERTICAL_ALIGN));

    $("#test").css(CSS.VERTICAL_ALIGN, VerticalAlign.MIDDLE);
    assertEquals("middle", $("#test").css("verticalAlign"));
    assertEquals("middle", $("#test").css(CSS.VERTICAL_ALIGN));

    $("#test").css(CSS.VERTICAL_ALIGN, VerticalAlign.SUB);
    assertEquals("sub", $("#test").css("verticalAlign"));
    assertEquals("sub", $("#test").css(CSS.VERTICAL_ALIGN));

    $("#test").css(CSS.VERTICAL_ALIGN, VerticalAlign.SUPER);
    assertEquals("super", $("#test").css("verticalAlign"));
    assertEquals("super", $("#test").css(CSS.VERTICAL_ALIGN));

    $("#test").css(CSS.VERTICAL_ALIGN, VerticalAlign.TEXT_BOTTOM);
    assertEquals("text-bottom", $("#test").css("verticalAlign"));
    assertEquals("text-bottom", $("#test").css(CSS.VERTICAL_ALIGN));

    $("#test").css(CSS.VERTICAL_ALIGN, VerticalAlign.TEXT_TOP);
    assertEquals("text-top", $("#test").css("verticalAlign"));
    assertEquals("text-top", $("#test").css(CSS.VERTICAL_ALIGN));

    $("#test").css(CSS.VERTICAL_ALIGN, VerticalAlign.TOP);
    assertEquals("top", $("#test").css("verticalAlign"));
    assertEquals("top", $("#test").css(CSS.VERTICAL_ALIGN));

  }
  
  public void testVisibilityProperty() {

    $(e).html("<div id='test'>Content</div>");

    $("#test").css(CSS.VISIBILITY, Visibility.HIDDEN);
    assertEquals("hidden", $("#test").css("visibility"));
    assertEquals("hidden", $("#test").css(CSS.VISIBILITY));

    $("#test").css(CSS.VISIBILITY, Visibility.VISIBLE);
    assertEquals("visible", $("#test").css("visibility"));
    assertEquals("visible", $("#test").css(CSS.VISIBILITY));

  }
  
  public void testWidthProperty() {

    $(e).html("<div id='test'>Content</div>");

    $("#test").css(CSS.WIDTH, Length.px(20));
    assertEquals("20px", $("#test").css("width"));
    assertEquals("20px", $("#test").css(CSS.WIDTH));

  }

}
