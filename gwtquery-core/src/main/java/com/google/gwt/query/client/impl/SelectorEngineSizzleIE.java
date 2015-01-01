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
package com.google.gwt.query.client.impl;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;

/**
 * Original Sizzle CSS Selector Engine v1.0 inserted in a JSNI method.
 * There are a couple of differences with the original:
 * - It uses the window.IES instead of window.Sizzle to avoid interfering.
 * - All the stuff related with non IE browsers has been removed making this
 *   implementation a bit faster and smaller.
 *
 * There are some reasons why we are using sizzle with IE instead of any other
 * implementation.
 *  - Sizzle is the faster selector with IE we have tested.
 *  - Only IE8 under some conditions and with a limited set of selectors
 *    has a native and fast method for selecting dom elements. So we need
 *    a complete implementation in javascript.
 *  - We don't want to make the effort of writing a complete js implementation (DRY).
 *  - It is supposed this class will be here for a while, because new versions of
 *    IE are coming.
 */
public class SelectorEngineSizzleIE extends SelectorEngineImpl {

  public static native void initialize()
  /*-{

(function(){

var chunker = /((?:\((?:\([^()]+\)|[^()]+)+\)|\[(?:\[[^\[\]]*\]|['"][^'"]*['"]|[^\[\]'"]+)+\]|\\.|[^ >+~,(\[\\]+)+|[>+~])(\s*,\s*)?((?:.|\r|\n)*)/g,
  done = 0,
  toString = Object.prototype.toString,
  hasDuplicate = false;

var IES = function(selector, context, results, seed) {
  results = results || [];
  context = context || document;

  var origContext = context;

  if ( context.nodeType !== 1 && context.nodeType !== 9 ) {
    return [];
  }

  if ( !selector || typeof selector !== "string" ) {
    return results;
  }

  var parts = [], m, set, checkSet, extra, prune = true, contextXML = IES.isXML(context),
    soFar = selector, ret, cur, pop, i;

  // Reset the position of the chunker regexp (start from head)
  do {
    chunker.exec("");
    m = chunker.exec(soFar);

    if ( m ) {
      soFar = m[3];

      parts.push( m[1] );

      if ( m[2] ) {
        extra = m[3];
        break;
      }
    }
  } while ( m );

  if ( parts.length > 1 && origPOS.exec( selector ) ) {
    if ( parts.length === 2 && Expr.relative[ parts[0] ] ) {
      set = posProcess( parts[0] + parts[1], context );
    } else {
      set = Expr.relative[ parts[0] ] ?
        [ context ] :
        IES( parts.shift(), context );

      while ( parts.length ) {
        selector = parts.shift();

        if ( Expr.relative[ selector ] ) {
          selector += parts.shift();
        }

        set = posProcess( selector, set );
      }
    }
  } else {
    // Take a shortcut and set the context if the root selector is an ID
    // (but not if it'll be faster if the inner selector is an ID)
    if ( !seed && parts.length > 1 && context.nodeType === 9 && !contextXML &&
        Expr.match.ID.test(parts[0]) && !Expr.match.ID.test(parts[parts.length - 1]) ) {
      ret = IES.find( parts.shift(), context, contextXML );
      context = ret.expr ? IES.filter( ret.expr, ret.set )[0] : ret.set[0];
    }

    if ( context ) {
      ret = seed ?
        { expr: parts.pop(), set: makeArray(seed) } :
        IES.find( parts.pop(), parts.length === 1 && (parts[0] === "~" || parts[0] === "+") && context.parentNode ? context.parentNode : context, contextXML );
      set = ret.expr ? IES.filter( ret.expr, ret.set ) : ret.set;

      if ( parts.length > 0 ) {
        checkSet = makeArray(set);
      } else {
        prune = false;
      }

      while ( parts.length ) {
        cur = parts.pop();
        pop = cur;

        if ( !Expr.relative[ cur ] ) {
          cur = "-";
        } else {
          pop = parts.pop();
        }

        if ( pop == null ) {
          pop = context;
        }

        Expr.relative[ cur ]( checkSet, pop, contextXML );
      }
    } else {
      checkSet = parts = [];
    }
  }

  if ( !checkSet ) {
    checkSet = set;
  }

  if ( !checkSet ) {
    IES.error( cur || selector );
  }

  if ( toString.call(checkSet) === "[object Array]" ) {
    if ( !prune ) {
      results.push.apply( results, checkSet );
    } else if ( context && context.nodeType === 1 ) {
      for ( i = 0; checkSet[i] != null; i++ ) {
        if ( checkSet[i] && (checkSet[i] === true || checkSet[i].nodeType === 1 && IES.contains(context, checkSet[i])) ) {
          results.push( set[i] );
        }
      }
    } else {
      for ( i = 0; checkSet[i] != null; i++ ) {
        if ( checkSet[i] && checkSet[i].nodeType === 1 ) {
          results.push( set[i] );
        }
      }
    }
  } else {
    makeArray( checkSet, results );
  }

  if ( extra ) {
    IES( extra, origContext, results, seed );
    IES.uniqueSort( results );
  }

  return results;
};

IES.uniqueSort = function(results){
  if ( sortOrder ) {
    hasDuplicate = false;
    results.sort(sortOrder);

    if ( hasDuplicate ) {
      for ( var i = 1; i < results.length; i++ ) {
        if ( results[i] === results[i-1] ) {
          results.splice(i--, 1);
        }
      }
    }
  }

  return results;
};

IES.matches = function(expr, set){
  return IES(expr, null, null, set);
};

IES.find = function(expr, context, isXML){
  var set;

  if ( !expr ) {
    return [];
  }

  for ( var i = 0, l = Expr.order.length; i < l; i++ ) {
    var type = Expr.order[i], match;

    if ( (match = Expr.leftMatch[ type ].exec( expr )) ) {
      var left = match[1];
      match.splice(1,1);

      if ( left.substr( left.length - 1 ) !== "\\" ) {
        match[1] = (match[1] || "").replace(/\\/g, "");
        set = Expr.find[ type ]( match, context, isXML );
        if ( set != null ) {
          expr = expr.replace( Expr.match[ type ], "" );
          break;
        }
      }
    }
  }

  if ( !set ) {
    set = context.getElementsByTagName("*");
  }

  return {set: set, expr: expr};
};

IES.filter = function(expr, set, inplace, not){
  var old = expr, result = [], curLoop = set, match, anyFound,
    isXMLFilter = set && set[0] && IES.isXML(set[0]);

  while ( expr && set.length ) {
    for ( var type in Expr.filter ) {
      if ( (match = Expr.leftMatch[ type ].exec( expr )) != null && match[2] ) {
        var filter = Expr.filter[ type ], found, item, left = match[1];
        anyFound = false;

        match.splice(1,1);

        if ( left.substr( left.length - 1 ) === "\\" ) {
          continue;
        }

        if ( curLoop === result ) {
          result = [];
        }

        if ( Expr.preFilter[ type ] ) {
          match = Expr.preFilter[ type ]( match, curLoop, inplace, result, not, isXMLFilter );

          if ( !match ) {
            anyFound = found = true;
          } else if ( match === true ) {
            continue;
          }
        }

        if ( match ) {
          for ( var i = 0; (item = curLoop[i]) != null; i++ ) {
            if ( item ) {
              found = filter( item, match, i, curLoop );
              var pass = not ^ !!found;

              if ( inplace && found != null ) {
                if ( pass ) {
                  anyFound = true;
                } else {
                  curLoop[i] = false;
                }
              } else if ( pass ) {
                result.push( item );
                anyFound = true;
              }
            }
          }
        }

        if ( found !== undefined ) {
          if ( !inplace ) {
            curLoop = result;
          }

          expr = expr.replace( Expr.match[ type ], "" );

          if ( !anyFound ) {
            return [];
          }

          break;
        }
      }
    }

    // Improper expression
    if ( expr === old ) {
      if ( anyFound == null ) {
        IES.error( expr );
      } else {
        break;
      }
    }

    old = expr;
  }

  return curLoop;
};

IES.error = function( msg ) {
  throw "Syntax error, unrecognized expression: " + msg;
};

var Expr = IES.selectors = {
  order: [ "ID", "NAME", "TAG" ],
  match: {
    ID: /#((?:[\w\u00c0-\uFFFF\-]|\\.)+)/,
    CLASS: /\.((?:[\w\u00c0-\uFFFF\-]|\\.)+)/,
    NAME: /\[name=['"]*((?:[\w\u00c0-\uFFFF\-]|\\.)+)['"]*\]/,
    ATTR: /\[\s*((?:[\w\u00c0-\uFFFF\-]|\\.)+)\s*(?:(\S?=)\s*(['"]*)(.*?)\3|)\s*\]/,
    TAG: /^((?:[\w\u00c0-\uFFFF\*\-]|\\.)+)/,
    CHILD: /:(only|nth|last|first)-child(?:\((even|odd|[\dn+\-]*)\))?/,
    POS: /:(nth|eq|gt|lt|first|last|even|odd)(?:\((\d*)\))?(?=[^\-]|$)/,
    PSEUDO: /:((?:[\w\u00c0-\uFFFF\-]|\\.)+)(?:\((['"]?)((?:\([^\)]+\)|[^\(\)]*)+)\2\))?/
  },
  leftMatch: {},
  attrMap: {
    "class": "className",
    "for": "htmlFor"
  },
  attrHandle: {
    href: function(elem){
      return elem.getAttribute("href", 2);
    }
  },
  relative: {
    "+": function(checkSet, part){
      var isPartStr = typeof part === "string",
        isTag = isPartStr && !/\W/.test(part),
        isPartStrNotTag = isPartStr && !isTag;

      if ( isTag ) {
        part = part.toLowerCase();
      }

      for ( var i = 0, l = checkSet.length, elem; i < l; i++ ) {
        if ( (elem = checkSet[i]) ) {
          while ( (elem = elem.previousSibling) && elem.nodeType !== 1 ) {}

          checkSet[i] = isPartStrNotTag || elem && elem.nodeName.toLowerCase() === part ?
            elem || false :
            elem === part;
        }
      }

      if ( isPartStrNotTag ) {
        IES.filter( part, checkSet, true );
      }
    },
    ">": function(checkSet, part){
      var isPartStr = typeof part === "string",
        elem, i = 0, l = checkSet.length;

      if ( isPartStr && !/\W/.test(part) ) {
        part = part.toLowerCase();

        for ( ; i < l; i++ ) {
          elem = checkSet[i];
          if ( elem ) {
            var parent = elem.parentNode;
            checkSet[i] = parent.nodeName.toLowerCase() === part ? parent : false;
          }
        }
      } else {
        for ( ; i < l; i++ ) {
          elem = checkSet[i];
          if ( elem ) {
            checkSet[i] = isPartStr ?
              elem.parentNode :
              elem.parentNode === part;
          }
        }

        if ( isPartStr ) {
          IES.filter( part, checkSet, true );
        }
      }
    },
    "-": function(checkSet, part, isXML){
      var doneName = done++, checkFn = dirCheck, nodeCheck;

      if ( typeof part === "string" && !/\W/.test(part) ) {
        part = part.toLowerCase();
        nodeCheck = part;
        checkFn = dirNodeCheck;
      }

      checkFn("parentNode", part, doneName, checkSet, nodeCheck, isXML);
    },
    "~": function(checkSet, part, isXML){
      var doneName = done++, checkFn = dirCheck, nodeCheck;

      if ( typeof part === "string" && !/\W/.test(part) ) {
        part = part.toLowerCase();
        nodeCheck = part;
        checkFn = dirNodeCheck;
      }

      checkFn("previousSibling", part, doneName, checkSet, nodeCheck, isXML);
    }
  },
  find: {
    ID: function(match, context, isXML){
      if ( typeof context.getElementById !== "undefined" && !isXML ) {
        var m = context.getElementById(match[1]);
        return m ? m.id === match[1] || typeof m.getAttributeNode !== "undefined" && m.getAttributeNode("id").nodeValue === match[1] ? [m] : undefined : [];
      }
    },
    NAME: function(match, context){
      if ( typeof context.getElementsByName !== "undefined" ) {
        var ret = [], results = context.getElementsByName(match[1]);

        for ( var i = 0, l = results.length; i < l; i++ ) {
          if ( results[i].getAttribute("name") === match[1] ) {
            ret.push( results[i] );
          }
        }

        return ret.length === 0 ? null : ret;
      }
    },
    // IE espedific
    TAG: function(match, context){
      var results = context.getElementsByTagName(match[1]);
      // Filter out possible comments
      if ( match[1] === "*" ) {
        var tmp = [];
        for ( var i = 0; results[i]; i++ ) {
          if ( results[i].nodeType === 1 ) {
            tmp.push( results[i] );
          }
        }
        results = tmp;
      }
      return results;
    }
  },
  preFilter: {
    CLASS: function(match, curLoop, inplace, result, not, isXML){
      match = " " + match[1].replace(/\\/g, "") + " ";

      if ( isXML ) {
        return match;
      }

      for ( var i = 0, elem; (elem = curLoop[i]) != null; i++ ) {
        if ( elem ) {
          if ( not ^ (elem.className && (" " + elem.className + " ").replace(/[\t\n]/g, " ").indexOf(match) >= 0) ) {
            if ( !inplace ) {
              result.push( elem );
            }
          } else if ( inplace ) {
            curLoop[i] = false;
          }
        }
      }

      return false;
    },
    ID: function(match){
      return match[1].replace(/\\/g, "");
    },
    TAG: function(match, curLoop){
      return match[1].toLowerCase();
    },
    CHILD: function(match){
      if ( match[1] === "nth" ) {
        // parse equations like 'even', 'odd', '5', '2n', '3n+2', '4n-1', '-n+6'
        var test = /(-?)(\d*)n((?:\+|-)?\d*)/.exec(
          match[2] === "even" && "2n" || match[2] === "odd" && "2n+1" ||
          !/\D/.test( match[2] ) && "0n+" + match[2] || match[2]);

        // calculate the numbers (first)n+(last) including if they are negative
        match[2] = (test[1] + (test[2] || 1)) - 0;
        match[3] = test[3] - 0;
      }

      // TODO: Move to normal caching system
      match[0] = done++;

      return match;
    },
    ATTR: function(match, curLoop, inplace, result, not, isXML){
      var name = match[1].replace(/\\/g, "");

      if ( !isXML && Expr.attrMap[name] ) {
        match[1] = Expr.attrMap[name];
      }

      if ( match[2] === "~=" ) {
        match[4] = " " + match[4] + " ";
      }

      return match;
    },
    PSEUDO: function(match, curLoop, inplace, result, not){
      if ( match[1] === "not" ) {
        // If we're dealing with a complex expression, or a simple one
        if ( ( chunker.exec(match[3]) || "" ).length > 1 || /^\w/.test(match[3]) ) {
          match[3] = IES(match[3], null, null, curLoop);
        } else {
          var ret = IES.filter(match[3], curLoop, inplace, true ^ not);
          if ( !inplace ) {
            result.push.apply( result, ret );
          }
          return false;
        }
      } else if ( Expr.match.POS.test( match[0] ) || Expr.match.CHILD.test( match[0] ) ) {
        return true;
      }

      return match;
    },
    POS: function(match){
      match.unshift( true );
      return match;
    }
  },
  filters: {
    enabled: function(elem){
      return elem.disabled === false && elem.type !== "hidden";
    },
    disabled: function(elem){
      return elem.disabled === true;
    },
    checked: function(elem){
      return elem.checked === true;
    },
    selected: function(elem){
      // Accessing this property makes selected-by-default
      // options in Safari work properly
      elem.parentNode.selectedIndex;
      return elem.selected === true;
    },
    parent: function(elem){
      return !!elem.firstChild;
    },
    empty: function(elem){
      return !elem.firstChild;
    },
    has: function(elem, i, match){
      return !!IES( match[3], elem ).length;
    },
    header: function(elem){
      return (/h\d/i).test( elem.nodeName );
    },
    text: function(elem){
      return "text" === elem.type;
    },
    radio: function(elem){
      return "radio" === elem.type;
    },
    checkbox: function(elem){
      return "checkbox" === elem.type;
    },
    file: function(elem){
      return "file" === elem.type;
    },
    password: function(elem){
      return "password" === elem.type;
    },
    submit: function(elem){
      return "submit" === elem.type;
    },
    image: function(elem){
      return "image" === elem.type;
    },
    reset: function(elem){
      return "reset" === elem.type;
    },
    button: function(elem){
      return "button" === elem.type || elem.nodeName.toLowerCase() === "button";
    },
    input: function(elem){
      return (/input|select|textarea|button/i).test(elem.nodeName);
    }
  },
  setFilters: {
    first: function(elem, i){
      return i === 0;
    },
    last: function(elem, i, match, array){
      return i === array.length - 1;
    },
    even: function(elem, i){
      return i % 2 === 0;
    },
    odd: function(elem, i){
      return i % 2 === 1;
    },
    lt: function(elem, i, match){
      return i < match[3] - 0;
    },
    gt: function(elem, i, match){
      return i > match[3] - 0;
    },
    nth: function(elem, i, match){
      return match[3] - 0 === i;
    },
    eq: function(elem, i, match){
      return match[3] - 0 === i;
    }
  },
  filter: {
    PSEUDO: function(elem, match, i, array){
      var name = match[1], filter = Expr.filters[ name ];

      if ( filter ) {
        return filter( elem, i, match, array );
      } else if ( name === "contains" ) {
        return (elem.textContent || elem.innerText || IES.getText([ elem ]) || "").indexOf(match[3]) >= 0;
      } else if ( name === "not" ) {
        var not = match[3];

        for ( var j = 0, l = not.length; j < l; j++ ) {
          if ( not[j] === elem ) {
            return false;
          }
        }

        return true;
      } else {
        IES.error( "Syntax error, unrecognized expression: " + name );
      }
    },
    CHILD: function(elem, match){
      var type = match[1], node = elem;
      switch (type) {
        case 'only':
        case 'first':
          while ( (node = node.previousSibling) )  {
            if ( node.nodeType === 1 ) {
              return false;
            }
          }
          if ( type === "first" ) {
            return true;
          }
          node = elem;
        case 'last':
          while ( (node = node.nextSibling) )  {
            if ( node.nodeType === 1 ) {
              return false;
            }
          }
          return true;
        case 'nth':
          var first = match[2], last = match[3];

          if ( first === 1 && last === 0 ) {
            return true;
          }

          var doneName = match[0],
            parent = elem.parentNode;

          if ( parent && (parent.sizcache !== doneName || !elem.nodeIndex) ) {
            var count = 0;
            for ( node = parent.firstChild; node; node = node.nextSibling ) {
              if ( node.nodeType === 1 ) {
                node.nodeIndex = ++count;
              }
            }
            parent.sizcache = doneName;
          }

          var diff = elem.nodeIndex - last;
          if ( first === 0 ) {
            return diff === 0;
          } else {
            return ( diff % first === 0 && diff / first >= 0 );
          }
      }
    },
    ID: function(elem, match){
      var node = typeof elem.getAttributeNode !== "undefined" && elem.getAttributeNode("id");
      return elem.nodeType === 1 && node && node.nodeValue === match;
    },
    TAG: function(elem, match){
      return (match === "*" && elem.nodeType === 1) || elem.nodeName.toLowerCase() === match;
    },
    CLASS: function(elem, match){
      return (" " + (elem.className || elem.getAttribute("class")) + " ")
        .indexOf( match ) > -1;
    },
    ATTR: function(elem, match){
      var name = match[1],
        result = Expr.attrHandle[ name ] ?
          Expr.attrHandle[ name ]( elem ) :
          elem[ name ] != null ?
            elem[ name ] :
            elem.getAttribute( name ),
        value = result + "",
        type = match[2],
        check = match[4];

      return result == null ?
        type === "!=" :
        type === "=" ?
        value === check :
        type === "*=" ?
        value.indexOf(check) >= 0 :
        type === "~=" ?
        (" " + value + " ").indexOf(check) >= 0 :
        !check ?
        value && result !== false :
        type === "!=" ?
        value !== check :
        type === "^=" ?
        value.indexOf(check) === 0 :
        type === "$=" ?
        value.substr(value.length - check.length) === check :
        type === "|=" ?
        value === check || value.substr(0, check.length + 1) === check + "-" :
        false;
    },
    POS: function(elem, match, i, array){
      var name = match[2], filter = Expr.setFilters[ name ];

      if ( filter ) {
        return filter( elem, i, match, array );
      }
    }
  }
};

var origPOS = Expr.match.POS,
  fescape = function(all, num){
    return "\\" + (num - 0 + 1);
  };

for ( var type in Expr.match ) {
  Expr.match[ type ] = new RegExp( Expr.match[ type ].source + (/(?![^\[]*\])(?![^\(]*\))/.source) );
  Expr.leftMatch[ type ] = new RegExp( /(^(?:.|\r|\n)*?)/.source + Expr.match[ type ].source.replace(/\\(\d+)/g, fescape) );
}

var makeArray = function(array, results) {
  array = Array.prototype.slice.call( array, 0 );

  if ( results ) {
    results.push.apply( results, array );
    return results;
  }

  return array;
};

// Perform a simple check to determine if the browser is capable of
// converting a NodeList to an array using builtin methods.
// Also verifies that the returned array holds DOM nodes
// (which is not the case in the Blackberry browser)
try {
  Array.prototype.slice.call( document.documentElement.childNodes, 0 )[0].nodeType;

// Provide a fallback method if it does not work
} catch(e){
  makeArray = function(array, results) {
    var ret = results || [], i = 0;

    if ( toString.call(array) === "[object Array]" ) {
      Array.prototype.push.apply( ret, array );
    } else {
      if ( typeof array.length === "number" ) {
        for ( var l = array.length; i < l; i++ ) {
          ret.push( array[i] );
        }
      } else {
        for ( ; array[i]; i++ ) {
          ret.push( array[i] );
        }
      }
    }

    return ret;
  };
}

var sortOrder = function( a, b ) {
    if ( !a.sourceIndex || !b.sourceIndex ) {
      if ( a == b ) {
        hasDuplicate = true;
      }
      return a.sourceIndex ? -1 : 1;
    }

    var ret = a.sourceIndex - b.sourceIndex;
    if ( ret === 0 ) {
      hasDuplicate = true;
    }
    return ret;
};

// Utility function for retreiving the text value of an array of DOM nodes
IES.getText = function( elems ) {
  var ret = "", elem;

  for ( var i = 0; elems[i]; i++ ) {
    elem = elems[i];

    // Get the text from text nodes and CDATA nodes
    if ( elem.nodeType === 3 || elem.nodeType === 4 ) {
      ret += elem.nodeValue;

    // Traverse everything else, except comment nodes
    } else if ( elem.nodeType !== 8 ) {
      ret += IES.getText( elem.childNodes );
    }
  }

  return ret;
};

function dirNodeCheck( dir, cur, doneName, checkSet, nodeCheck, isXML ) {
  for ( var i = 0, l = checkSet.length; i < l; i++ ) {
    var elem = checkSet[i];
    if ( elem ) {
      elem = elem[dir];
      var match = false;

      while ( elem ) {
        if ( elem.sizcache === doneName ) {
          match = checkSet[elem.sizset];
          break;
        }

        if ( elem.nodeType === 1 && !isXML ){
          elem.sizcache = doneName;
          elem.sizset = i;
        }

        if ( elem.nodeName.toLowerCase() === cur ) {
          match = elem;
          break;
        }

        elem = elem[dir];
      }

      checkSet[i] = match;
    }
  }
}

function dirCheck( dir, cur, doneName, checkSet, nodeCheck, isXML ) {
  for ( var i = 0, l = checkSet.length; i < l; i++ ) {
    var elem = checkSet[i];
    if ( elem ) {
      elem = elem[dir];
      var match = false;

      while ( elem ) {
        if ( elem.sizcache === doneName ) {
          match = checkSet[elem.sizset];
          break;
        }

        if ( elem.nodeType === 1 ) {
          if ( !isXML ) {
            elem.sizcache = doneName;
            elem.sizset = i;
          }
          if ( typeof cur !== "string" ) {
            if ( elem === cur ) {
              match = true;
              break;
            }
          } else if ( IES.filter( cur, [elem] ).length > 0 ) {
            match = elem;
            break;
          }
        }

        elem = elem[dir];
      }

      checkSet[i] = match;
    }
  }
}

IES.contains = function(a, b) {
  return a !== b && (a.contains ? a.contains(b) : true);
};

IES.isXML = function(elem){
  // documentElement is verified for cases where it doesn't yet exist
  // (such as loading iframes in IE - #4833)
  var documentElement = (elem ? elem.ownerDocument || elem : 0).documentElement;
  return documentElement ? documentElement.nodeName !== "HTML" : false;
};

var posProcess = function(selector, context){
  var tmpSet = [], later = "", match,
    root = context.nodeType ? [context] : context;

  // Position selectors must be done after the filter
  // And so must :not(positional) so we move all PSEUDOs to the end
  while ( (match = Expr.match.PSEUDO.exec( selector )) ) {
    later += match[0];
    selector = selector.replace( Expr.match.PSEUDO, "" );
  }

  selector = Expr.relative[selector] ? selector + "*" : selector;

  for ( var i = 0, l = root.length; i < l; i++ ) {
    IES( selector, root[i], tmpSet );
  }

  return IES.filter( later, tmpSet );
};

// EXPOSE

window.IES = IES;
$wnd.IES = IES;
})();
  }-*/;

  private static native JsArray<Element> select(String selector, Node context, JsArray<Element> results, JsArray<Element> seed)
  /*-{

    return $wnd.IES(selector, context, results, seed);
  }-*/;

  static boolean initialized = false;

  public SelectorEngineSizzleIE() {
    if (!initialized) {
      initialize();
    }
  }

  public NodeList<Element> select(String selector, Node context) {
    JsArray<Element> results = JavaScriptObject.createArray().cast();
    return select(selector, context, results, null).cast();
  }
}
