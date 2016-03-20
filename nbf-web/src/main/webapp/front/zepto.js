// Zepto 1.1.6 (generated with Zepto Builder) - zepto event ajax form ie fx_methods fx - zeptojs.com/license
var Zepto = function() {
    function t(t) {
        return null == t ? String(t) : J[W.call(t)] || "object"
    }

    function e(e) {
        return "function" == t(e)
    }

    function n(t) {
        return null != t && t == t.window
    }

    function r(t) {
        return null != t && t.nodeType == t.DOCUMENT_NODE
    }

    function i(e) {
        return "object" == t(e)
    }

    function o(t) {
        return i(t) && !n(t) && Object.getPrototypeOf(t) == Object.prototype
    }

    function s(t) {
        return "number" == typeof t.length
    }

    function a(t) {
        return A.call(t, function(t) {
            return null != t
        })
    }

    function u(t) {
        return t.length > 0 ? T.fn.concat.apply([], t) : t
    }

    function c(t) {
        return t.replace(/::/g, "/").replace(/([A-Z]+)([A-Z][a-z])/g, "$1_$2").replace(/([a-z\d])([A-Z])/g, "$1_$2").replace(/_/g, "-").toLowerCase()
    }

    function l(t) {
        return t in D ? D[t] : D[t] = new RegExp("(^|\\s)" + t + "(\\s|$)")
    }

    function f(t, e) {
        return "number" != typeof e || M[c(t)] ? e : e + "px"
    }

    function h(t) {
        var e, n;
        return Z[t] || (e = $.createElement(t), $.body.appendChild(e), n = getComputedStyle(e, "").getPropertyValue("display"), e.parentNode.removeChild(e), "none" == n && (n = "block"), Z[t] = n), Z[t]
    }

    function p(t) {
        return "children" in t ? L.call(t.children) : T.map(t.childNodes, function(t) {
            return 1 == t.nodeType ? t : void 0
        })
    }

    function d(t, e) {
        var n, r = t ? t.length : 0;
        for (n = 0; r > n; n++) this[n] = t[n];
        this.length = r, this.selector = e || ""
    }

    function m(t, e, n) {
        for (j in e) n && (o(e[j]) || Q(e[j])) ? (o(e[j]) && !o(t[j]) && (t[j] = {}), Q(e[j]) && !Q(t[j]) && (t[j] = []), m(t[j], e[j], n)) : e[j] !== E && (t[j] = e[j])
    }

    function g(t, e) {
        return null == e ? T(t) : T(t).filter(e)
    }

    function v(t, n, r, i) {
        return e(n) ? n.call(t, r, i) : n
    }

    function y(t, e, n) {
        null == n ? t.removeAttribute(e) : t.setAttribute(e, n)
    }

    function x(t, e) {
        var n = t.className || "",
            r = n && n.baseVal !== E;
        return e === E ? r ? n.baseVal : n : void(r ? n.baseVal = e : t.className = e)
    }

    function b(t) {
        try {
            return t ? "true" == t || ("false" == t ? !1 : "null" == t ? null : +t + "" == t ? +t : /^[\[\{]/.test(t) ? T.parseJSON(t) : t) : t
        } catch (e) {
            return t
        }
    }

    function w(t, e) {
        e(t);
        for (var n = 0, r = t.childNodes.length; r > n; n++) w(t.childNodes[n], e)
    }
    var E, j, T, C, S, N, O = [],
        P = O.concat,
        A = O.filter,
        L = O.slice,
        $ = window.document,
        Z = {},
        D = {},
        M = {
            "column-count": 1,
            columns: 1,
            "font-weight": 1,
            "line-height": 1,
            opacity: 1,
            "z-index": 1,
            zoom: 1
        },
        k = /^\s*<(\w+|!)[^>]*>/,
        R = /^<(\w+)\s*\/?>(?:<\/\1>|)$/,
        F = /<(?!area|br|col|embed|hr|img|input|link|meta|param)(([\w:]+)[^>]*)\/>/gi,
        z = /^(?:body|html)$/i,
        q = /([A-Z])/g,
        _ = ["val", "css", "html", "text", "data", "width", "height", "offset"],
        H = ["after", "prepend", "before", "append"],
        I = $.createElement("table"),
        B = $.createElement("tr"),
        V = {
            tr: $.createElement("tbody"),
            tbody: I,
            thead: I,
            tfoot: I,
            td: B,
            th: B,
            "*": $.createElement("div")
        },
        U = /complete|loaded|interactive/,
        X = /^[\w-]*$/,
        J = {},
        W = J.toString,
        Y = {},
        G = $.createElement("div"),
        K = {
            tabindex: "tabIndex",
            readonly: "readOnly",
            "for": "htmlFor",
            "class": "className",
            maxlength: "maxLength",
            cellspacing: "cellSpacing",
            cellpadding: "cellPadding",
            rowspan: "rowSpan",
            colspan: "colSpan",
            usemap: "useMap",
            frameborder: "frameBorder",
            contenteditable: "contentEditable"
        },
        Q = Array.isArray || function(t) {
                return t instanceof Array
            };
    return Y.matches = function(t, e) {
        if (!e || !t || 1 !== t.nodeType) return !1;
        var n = t.webkitMatchesSelector || t.mozMatchesSelector || t.oMatchesSelector || t.matchesSelector;
        if (n) return n.call(t, e);
        var r, i = t.parentNode,
            o = !i;
        return o && (i = G).appendChild(t), r = ~Y.qsa(i, e).indexOf(t), o && G.removeChild(t), r
    }, S = function(t) {
        return t.replace(/-+(.)?/g, function(t, e) {
            return e ? e.toUpperCase() : ""
        })
    }, N = function(t) {
        return A.call(t, function(e, n) {
            return t.indexOf(e) == n
        })
    }, Y.fragment = function(t, e, n) {
        var r, i, s;
        return R.test(t) && (r = T($.createElement(RegExp.$1))), r || (t.replace && (t = t.replace(F, "<$1></$2>")), e === E && (e = k.test(t) && RegExp.$1), e in V || (e = "*"), s = V[e], s.innerHTML = "" + t, r = T.each(L.call(s.childNodes), function() {
            s.removeChild(this)
        })), o(n) && (i = T(r), T.each(n, function(t, e) {
            _.indexOf(t) > -1 ? i[t](e) : i.attr(t, e)
        })), r
    }, Y.Z = function(t, e) {
        return new d(t, e)
    }, Y.isZ = function(t) {
        return t instanceof Y.Z
    }, Y.init = function(t, n) {
        var r;
        if (!t) return Y.Z();
        if ("string" == typeof t)
            if (t = t.trim(), "<" == t[0] && k.test(t)) r = Y.fragment(t, RegExp.$1, n), t = null;
            else {
                if (n !== E) return T(n).find(t);
                r = Y.qsa($, t)
            } else {
            if (e(t)) return T($).ready(t);
            if (Y.isZ(t)) return t;
            if (Q(t)) r = a(t);
            else if (i(t)) r = [t], t = null;
            else if (k.test(t)) r = Y.fragment(t.trim(), RegExp.$1, n), t = null;
            else {
                if (n !== E) return T(n).find(t);
                r = Y.qsa($, t)
            }
        }
        return Y.Z(r, t)
    }, T = function(t, e) {
        return Y.init(t, e)
    }, T.extend = function(t) {
        var e, n = L.call(arguments, 1);
        return "boolean" == typeof t && (e = t, t = n.shift()), n.forEach(function(n) {
            m(t, n, e)
        }), t
    }, Y.qsa = function(t, e) {
        var n, r = "#" == e[0],
            i = !r && "." == e[0],
            o = r || i ? e.slice(1) : e,
            s = X.test(o);
        return t.getElementById && s && r ? (n = t.getElementById(o)) ? [n] : [] : 1 !== t.nodeType && 9 !== t.nodeType && 11 !== t.nodeType ? [] : L.call(s && !r && t.getElementsByClassName ? i ? t.getElementsByClassName(o) : t.getElementsByTagName(e) : t.querySelectorAll(e))
    }, T.contains = $.documentElement.contains ? function(t, e) {
        return t !== e && t.contains(e)
    } : function(t, e) {
        for (; e && (e = e.parentNode);)
            if (e === t) return !0;
        return !1
    }, T.type = t, T.isFunction = e, T.isWindow = n, T.isArray = Q, T.isPlainObject = o, T.isEmptyObject = function(t) {
        var e;
        for (e in t) return !1;
        return !0
    }, T.inArray = function(t, e, n) {
        return O.indexOf.call(e, t, n)
    }, T.camelCase = S, T.trim = function(t) {
        return null == t ? "" : String.prototype.trim.call(t)
    }, T.uuid = 0, T.support = {}, T.expr = {}, T.noop = function() {}, T.map = function(t, e) {
        var n, r, i, o = [];
        if (s(t))
            for (r = 0; r < t.length; r++) n = e(t[r], r), null != n && o.push(n);
        else
            for (i in t) n = e(t[i], i), null != n && o.push(n);
        return u(o)
    }, T.each = function(t, e) {
        var n, r;
        if (s(t)) {
            for (n = 0; n < t.length; n++)
                if (e.call(t[n], n, t[n]) === !1) return t
        } else
            for (r in t)
                if (e.call(t[r], r, t[r]) === !1) return t; return t
    }, T.grep = function(t, e) {
        return A.call(t, e)
    }, window.JSON && (T.parseJSON = JSON.parse), T.each("Boolean Number String Function Array Date RegExp Object Error".split(" "), function(t, e) {
        J["[object " + e + "]"] = e.toLowerCase()
    }), T.fn = {
        constructor: Y.Z,
        length: 0,
        forEach: O.forEach,
        reduce: O.reduce,
        push: O.push,
        sort: O.sort,
        splice: O.splice,
        indexOf: O.indexOf,
        concat: function() {
            var t, e, n = [];
            for (t = 0; t < arguments.length; t++) e = arguments[t], n[t] = Y.isZ(e) ? e.toArray() : e;
            return P.apply(Y.isZ(this) ? this.toArray() : this, n)
        },
        map: function(t) {
            return T(T.map(this, function(e, n) {
                return t.call(e, n, e)
            }))
        },
        slice: function() {
            return T(L.apply(this, arguments))
        },
        ready: function(t) {
            return U.test($.readyState) && $.body ? t(T) : $.addEventListener("DOMContentLoaded", function() {
                t(T)
            }, !1), this
        },
        get: function(t) {
            return t === E ? L.call(this) : this[t >= 0 ? t : t + this.length]
        },
        toArray: function() {
            return this.get()
        },
        size: function() {
            return this.length
        },
        remove: function() {
            return this.each(function() {
                null != this.parentNode && this.parentNode.removeChild(this)
            })
        },
        each: function(t) {
            return O.every.call(this, function(e, n) {
                return t.call(e, n, e) !== !1
            }), this
        },
        filter: function(t) {
            return e(t) ? this.not(this.not(t)) : T(A.call(this, function(e) {
                return Y.matches(e, t)
            }))
        },
        add: function(t, e) {
            return T(N(this.concat(T(t, e))))
        },
        is: function(t) {
            return this.length > 0 && Y.matches(this[0], t)
        },
        not: function(t) {
            var n = [];
            if (e(t) && t.call !== E) this.each(function(e) {
                t.call(this, e) || n.push(this)
            });
            else {
                var r = "string" == typeof t ? this.filter(t) : s(t) && e(t.item) ? L.call(t) : T(t);
                this.forEach(function(t) {
                    r.indexOf(t) < 0 && n.push(t)
                })
            }
            return T(n)
        },
        has: function(t) {
            return this.filter(function() {
                return i(t) ? T.contains(this, t) : T(this).find(t).size()
            })
        },
        eq: function(t) {
            return -1 === t ? this.slice(t) : this.slice(t, +t + 1)
        },
        first: function() {
            var t = this[0];
            return t && !i(t) ? t : T(t)
        },
        last: function() {
            var t = this[this.length - 1];
            return t && !i(t) ? t : T(t)
        },
        find: function(t) {
            var e, n = this;
            return e = t ? "object" == typeof t ? T(t).filter(function() {
                var t = this;
                return O.some.call(n, function(e) {
                    return T.contains(e, t)
                })
            }) : 1 == this.length ? T(Y.qsa(this[0], t)) : this.map(function() {
                return Y.qsa(this, t)
            }) : T()
        },
        closest: function(t, e) {
            var n = this[0],
                i = !1;
            for ("object" == typeof t && (i = T(t)); n && !(i ? i.indexOf(n) >= 0 : Y.matches(n, t));) n = n !== e && !r(n) && n.parentNode;
            return T(n)
        },
        parents: function(t) {
            for (var e = [], n = this; n.length > 0;) n = T.map(n, function(t) {
                return (t = t.parentNode) && !r(t) && e.indexOf(t) < 0 ? (e.push(t), t) : void 0
            });
            return g(e, t)
        },
        parent: function(t) {
            return g(N(this.pluck("parentNode")), t)
        },
        children: function(t) {
            return g(this.map(function() {
                return p(this)
            }), t)
        },
        contents: function() {
            return this.map(function() {
                return this.contentDocument || L.call(this.childNodes)
            })
        },
        siblings: function(t) {
            return g(this.map(function(t, e) {
                return A.call(p(e.parentNode), function(t) {
                    return t !== e
                })
            }), t)
        },
        empty: function() {
            return this.each(function() {
                this.innerHTML = ""
            })
        },
        pluck: function(t) {
            return T.map(this, function(e) {
                return e[t]
            })
        },
        show: function() {
            return this.each(function() {
                "none" == this.style.display && (this.style.display = ""), "none" == getComputedStyle(this, "").getPropertyValue("display") && (this.style.display = h(this.nodeName))
            })
        },
        replaceWith: function(t) {
            return this.before(t).remove()
        },
        wrap: function(t) {
            var n = e(t);
            if (this[0] && !n) var r = T(t).get(0),
                i = r.parentNode || this.length > 1;
            return this.each(function(e) {
                T(this).wrapAll(n ? t.call(this, e) : i ? r.cloneNode(!0) : r)
            })
        },
        wrapAll: function(t) {
            if (this[0]) {
                T(this[0]).before(t = T(t));
                for (var e;
                     (e = t.children()).length;) t = e.first();
                T(t).append(this)
            }
            return this
        },
        wrapInner: function(t) {
            var n = e(t);
            return this.each(function(e) {
                var r = T(this),
                    i = r.contents(),
                    o = n ? t.call(this, e) : t;
                i.length ? i.wrapAll(o) : r.append(o)
            })
        },
        unwrap: function() {
            return this.parent().each(function() {
                T(this).replaceWith(T(this).children())
            }), this
        },
        clone: function() {
            return this.map(function() {
                return this.cloneNode(!0)
            })
        },
        hide: function() {
            return this.css("display", "none")
        },
        toggle: function(t) {
            return this.each(function() {
                var e = T(this);
                (t === E ? "none" == e.css("display") : t) ? e.show(): e.hide()
            })
        },
        prev: function(t) {
            return T(this.pluck("previousElementSibling")).filter(t || "*")
        },
        next: function(t) {
            return T(this.pluck("nextElementSibling")).filter(t || "*")
        },
        html: function(t) {
            return 0 in arguments ? this.each(function(e) {
                var n = this.innerHTML;
                T(this).empty().append(v(this, t, e, n))
            }) : 0 in this ? this[0].innerHTML : null
        },
        text: function(t) {
            return 0 in arguments ? this.each(function(e) {
                var n = v(this, t, e, this.textContent);
                this.textContent = null == n ? "" : "" + n
            }) : 0 in this ? this[0].textContent : null
        },
        attr: function(t, e) {
            var n;
            return "string" != typeof t || 1 in arguments ? this.each(function(n) {
                if (1 === this.nodeType)
                    if (i(t))
                        for (j in t) y(this, j, t[j]);
                    else y(this, t, v(this, e, n, this.getAttribute(t)))
            }) : this.length && 1 === this[0].nodeType ? !(n = this[0].getAttribute(t)) && t in this[0] ? this[0][t] : n : E
        },
        removeAttr: function(t) {
            return this.each(function() {
                1 === this.nodeType && t.split(" ").forEach(function(t) {
                    y(this, t)
                }, this)
            })
        },
        prop: function(t, e) {
            return t = K[t] || t, 1 in arguments ? this.each(function(n) {
                this[t] = v(this, e, n, this[t])
            }) : this[0] && this[0][t]
        },
        data: function(t, e) {
            var n = "data-" + t.replace(q, "-$1").toLowerCase(),
                r = 1 in arguments ? this.attr(n, e) : this.attr(n);
            return null !== r ? b(r) : E
        },
        val: function(t) {
            return 0 in arguments ? this.each(function(e) {
                this.value = v(this, t, e, this.value)
            }) : this[0] && (this[0].multiple ? T(this[0]).find("option").filter(function() {
                return this.selected
            }).pluck("value") : this[0].value)
        },
        offset: function(t) {
            if (t) return this.each(function(e) {
                var n = T(this),
                    r = v(this, t, e, n.offset()),
                    i = n.offsetParent().offset(),
                    o = {
                        top: r.top - i.top,
                        left: r.left - i.left
                    };
                "static" == n.css("position") && (o.position = "relative"), n.css(o)
            });
            if (!this.length) return null;
            if (!T.contains($.documentElement, this[0])) return {
                top: 0,
                left: 0
            };
            var e = this[0].getBoundingClientRect();
            return {
                left: e.left + window.pageXOffset,
                top: e.top + window.pageYOffset,
                width: Math.round(e.width),
                height: Math.round(e.height)
            }
        },
        css: function(e, n) {
            if (arguments.length < 2) {
                var r, i = this[0];
                if (!i) return;
                if (r = getComputedStyle(i, ""), "string" == typeof e) return i.style[S(e)] || r.getPropertyValue(e);
                if (Q(e)) {
                    var o = {};
                    return T.each(e, function(t, e) {
                        o[e] = i.style[S(e)] || r.getPropertyValue(e)
                    }), o
                }
            }
            var s = "";
            if ("string" == t(e)) n || 0 === n ? s = c(e) + ":" + f(e, n) : this.each(function() {
                this.style.removeProperty(c(e))
            });
            else
                for (j in e) e[j] || 0 === e[j] ? s += c(j) + ":" + f(j, e[j]) + ";" : this.each(function() {
                    this.style.removeProperty(c(j))
                });
            return this.each(function() {
                this.style.cssText += ";" + s
            })
        },
        index: function(t) {
            return t ? this.indexOf(T(t)[0]) : this.parent().children().indexOf(this[0])
        },
        hasClass: function(t) {
            return t ? O.some.call(this, function(t) {
                return this.test(x(t))
            }, l(t)) : !1
        },
        addClass: function(t) {
            return t ? this.each(function(e) {
                if ("className" in this) {
                    C = [];
                    var n = x(this),
                        r = v(this, t, e, n);
                    r.split(/\s+/g).forEach(function(t) {
                        T(this).hasClass(t) || C.push(t)
                    }, this), C.length && x(this, n + (n ? " " : "") + C.join(" "))
                }
            }) : this
        },
        removeClass: function(t) {
            return this.each(function(e) {
                if ("className" in this) {
                    if (t === E) return x(this, "");
                    C = x(this), v(this, t, e, C).split(/\s+/g).forEach(function(t) {
                        C = C.replace(l(t), " ")
                    }), x(this, C.trim())
                }
            })
        },
        toggleClass: function(t, e) {
            return t ? this.each(function(n) {
                var r = T(this),
                    i = v(this, t, n, x(this));
                i.split(/\s+/g).forEach(function(t) {
                    (e === E ? !r.hasClass(t) : e) ? r.addClass(t): r.removeClass(t)
                })
            }) : this
        },
        scrollTop: function(t) {
            if (this.length) {
                var e = "scrollTop" in this[0];
                return t === E ? e ? this[0].scrollTop : this[0].pageYOffset : this.each(e ? function() {
                    this.scrollTop = t
                } : function() {
                    this.scrollTo(this.scrollX, t)
                })
            }
        },
        scrollLeft: function(t) {
            if (this.length) {
                var e = "scrollLeft" in this[0];
                return t === E ? e ? this[0].scrollLeft : this[0].pageXOffset : this.each(e ? function() {
                    this.scrollLeft = t
                } : function() {
                    this.scrollTo(t, this.scrollY)
                })
            }
        },
        position: function() {
            if (this.length) {
                var t = this[0],
                    e = this.offsetParent(),
                    n = this.offset(),
                    r = z.test(e[0].nodeName) ? {
                        top: 0,
                        left: 0
                    } : e.offset();
                return n.top -= parseFloat(T(t).css("margin-top")) || 0, n.left -= parseFloat(T(t).css("margin-left")) || 0, r.top += parseFloat(T(e[0]).css("border-top-width")) || 0, r.left += parseFloat(T(e[0]).css("border-left-width")) || 0, {
                    top: n.top - r.top,
                    left: n.left - r.left
                }
            }
        },
        offsetParent: function() {
            return this.map(function() {
                for (var t = this.offsetParent || $.body; t && !z.test(t.nodeName) && "static" == T(t).css("position");) t = t.offsetParent;
                return t
            })
        }
    }, T.fn.detach = T.fn.remove, ["width", "height"].forEach(function(t) {
        var e = t.replace(/./, function(t) {
            return t[0].toUpperCase()
        });
        T.fn[t] = function(i) {
            var o, s = this[0];
            return i === E ? n(s) ? s["inner" + e] : r(s) ? s.documentElement["scroll" + e] : (o = this.offset()) && o[t] : this.each(function(e) {
                s = T(this), s.css(t, v(this, i, e, s[t]()))
            })
        }
    }), H.forEach(function(e, n) {
        var r = n % 2;
        T.fn[e] = function() {
            var e, i, o = T.map(arguments, function(n) {
                    return e = t(n), "object" == e || "array" == e || null == n ? n : Y.fragment(n)
                }),
                s = this.length > 1;
            return o.length < 1 ? this : this.each(function(t, e) {
                i = r ? e : e.parentNode, e = 0 == n ? e.nextSibling : 1 == n ? e.firstChild : 2 == n ? e : null;
                var a = T.contains($.documentElement, i);
                o.forEach(function(t) {
                    if (s) t = t.cloneNode(!0);
                    else if (!i) return T(t).remove();
                    i.insertBefore(t, e), a && w(t, function(t) {
                        null == t.nodeName || "SCRIPT" !== t.nodeName.toUpperCase() || t.type && "text/javascript" !== t.type || t.src || window.eval.call(window, t.innerHTML)
                    })
                })
            })
        }, T.fn[r ? e + "To" : "insert" + (n ? "Before" : "After")] = function(t) {
            return T(t)[e](this), this
        }
    }), Y.Z.prototype = d.prototype = T.fn, Y.uniq = N, Y.deserializeValue = b, T.zepto = Y, T
}();
window.Zepto = Zepto, void 0 === window.$ && (window.$ = Zepto),
    function(t) {
        function e(e, n, r) {
            var i = t.Event(n);
            return t(e).trigger(i, r), !i.isDefaultPrevented()
        }

        function n(t, n, r, i) {
            return t.global ? e(n || y, r, i) : void 0
        }

        function r(e) {
            e.global && 0 === t.active++ && n(e, null, "ajaxStart")
        }

        function i(e) {
            e.global && !--t.active && n(e, null, "ajaxStop")
        }

        function o(t, e) {
            var r = e.context;
            return e.beforeSend.call(r, t, e) === !1 || n(e, r, "ajaxBeforeSend", [t, e]) === !1 ? !1 : void n(e, r, "ajaxSend", [t, e])
        }

        function s(t, e, r, i) {
            var o = r.context,
                s = "success";
            r.success.call(o, t, s, e), i && i.resolveWith(o, [t, s, e]), n(r, o, "ajaxSuccess", [e, r, t]), u(s, e, r)
        }

        function a(t, e, r, i, o) {
            var s = i.context;
            i.error.call(s, r, e, t), o && o.rejectWith(s, [r, e, t]), n(i, s, "ajaxError", [r, i, t || e]), u(e, r, i)
        }

        function u(t, e, r) {
            var o = r.context;
            r.complete.call(o, e, t), n(r, o, "ajaxComplete", [e, r]), i(r)
        }

        function c() {}

        function l(t) {
            return t && (t = t.split(";", 2)[0]), t && (t == j ? "html" : t == E ? "json" : b.test(t) ? "script" : w.test(t) && "xml") || "text"
        }

        function f(t, e) {
            return "" == e ? t : (t + "&" + e).replace(/[&?]{1,2}/, "?")
        }

        function h(e) {
            e.processData && e.data && "string" != t.type(e.data) && (e.data = t.param(e.data, e.traditional)), !e.data || e.type && "GET" != e.type.toUpperCase() || (e.url = f(e.url, e.data), e.data = void 0)
        }

        function p(e, n, r, i) {
            return t.isFunction(n) && (i = r, r = n, n = void 0), t.isFunction(r) || (i = r, r = void 0), {
                url: e,
                data: n,
                success: r,
                dataType: i
            }
        }

        function d(e, n, r, i) {
            var o, s = t.isArray(n),
                a = t.isPlainObject(n);
            t.each(n, function(n, u) {
                o = t.type(u), i && (n = r ? i : i + "[" + (a || "object" == o || "array" == o ? n : "") + "]"), !i && s ? e.add(u.name, u.value) : "array" == o || !r && "object" == o ? d(e, u, r, n) : e.add(n, u)
            })
        }
        var m, g, v = 0,
            y = window.document,
            x = /<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/gi,
            b = /^(?:text|application)\/javascript/i,
            w = /^(?:text|application)\/xml/i,
            E = "application/json",
            j = "text/html",
            T = /^\s*$/,
            C = y.createElement("a");
        C.href = window.location.href, t.active = 0, t.ajaxJSONP = function(e, n) {
            if (!("type" in e)) return t.ajax(e);
            var r, i, u = e.jsonpCallback,
                c = (t.isFunction(u) ? u() : u) || "jsonp" + ++v,
                l = y.createElement("script"),
                f = window[c],
                h = function(e) {
                    t(l).triggerHandler("error", e || "abort")
                },
                p = {
                    abort: h
                };
            return n && n.promise(p), t(l).on("load error", function(o, u) {
                clearTimeout(i), t(l).off().remove(), "error" != o.type && r ? s(r[0], p, e, n) : a(null, u || "error", p, e, n), window[c] = f, r && t.isFunction(f) && f(r[0]), f = r = void 0
            }), o(p, e) === !1 ? (h("abort"), p) : (window[c] = function() {
                r = arguments
            }, l.src = e.url.replace(/\?(.+)=\?/, "?$1=" + c), y.head.appendChild(l), e.timeout > 0 && (i = setTimeout(function() {
                h("timeout")
            }, e.timeout)), p)
        }, t.ajaxSettings = {
            type: "GET",
            beforeSend: c,
            success: c,
            error: c,
            complete: c,
            context: null,
            global: !0,
            xhr: function() {
                return new window.XMLHttpRequest
            },
            accepts: {
                script: "text/javascript, application/javascript, application/x-javascript",
                json: E,
                xml: "application/xml, text/xml",
                html: j,
                text: "text/plain"
            },
            crossDomain: !1,
            timeout: 0,
            processData: !0,
            cache: !0
        }, t.ajax = function(e) {
            var n, i, u = t.extend({}, e || {}),
                p = t.Deferred && t.Deferred();
            for (m in t.ajaxSettings) void 0 === u[m] && (u[m] = t.ajaxSettings[m]);
            r(u), u.crossDomain || (n = y.createElement("a"), n.href = u.url, n.href = n.href, u.crossDomain = C.protocol + "//" + C.host != n.protocol + "//" + n.host), u.url || (u.url = window.location.toString()), (i = u.url.indexOf("#")) > -1 && (u.url = u.url.slice(0, i)), h(u);
            var d = u.dataType,
                v = /\?.+=\?/.test(u.url);
            if (v && (d = "jsonp"), u.cache !== !1 && (e && e.cache === !0 || "script" != d && "jsonp" != d) || (u.url = f(u.url, "_=" + Date.now())), "jsonp" == d) return v || (u.url = f(u.url, u.jsonp ? u.jsonp + "=?" : u.jsonp === !1 ? "" : "callback=?")), t.ajaxJSONP(u, p);
            var x, b = u.accepts[d],
                w = {},
                E = function(t, e) {
                    w[t.toLowerCase()] = [t, e]
                },
                j = /^([\w-]+:)\/\//.test(u.url) ? RegExp.$1 : window.location.protocol,
                S = u.xhr(),
                N = S.setRequestHeader;
            if (p && p.promise(S), u.crossDomain || E("X-Requested-With", "XMLHttpRequest"), E("Accept", b || "*/*"), (b = u.mimeType || b) && (b.indexOf(",") > -1 && (b = b.split(",", 2)[0]), S.overrideMimeType && S.overrideMimeType(b)), (u.contentType || u.contentType !== !1 && u.data && "GET" != u.type.toUpperCase()) && E("Content-Type", u.contentType || "application/x-www-form-urlencoded"), u.headers)
                for (g in u.headers) E(g, u.headers[g]);
            if (S.setRequestHeader = E, S.onreadystatechange = function() {
                    if (4 == S.readyState) {
                        S.onreadystatechange = c, clearTimeout(x);
                        var e, n = !1;
                        if (S.status >= 200 && S.status < 300 || 304 == S.status || 0 == S.status && "file:" == j) {
                            d = d || l(u.mimeType || S.getResponseHeader("content-type")), e = S.responseText;
                            try {
                                "script" == d ? (1, eval)(e) : "xml" == d ? e = S.responseXML : "json" == d && (e = T.test(e) ? null : t.parseJSON(e))
                            } catch (r) {
                                n = r
                            }
                            n ? a(n, "parsererror", S, u, p) : s(e, S, u, p)
                        } else a(S.statusText || null, S.status ? "error" : "abort", S, u, p)
                    }
                }, o(S, u) === !1) return S.abort(), a(null, "abort", S, u, p), S;
            if (u.xhrFields)
                for (g in u.xhrFields) S[g] = u.xhrFields[g];
            var O = "async" in u ? u.async : !0;
            S.open(u.type, u.url, O, u.username, u.password);
            for (g in w) N.apply(S, w[g]);
            return u.timeout > 0 && (x = setTimeout(function() {
                S.onreadystatechange = c, S.abort(), a(null, "timeout", S, u, p)
            }, u.timeout)), S.send(u.data ? u.data : null), S
        }, t.get = function() {
            return t.ajax(p.apply(null, arguments))
        }, t.post = function() {
            var e = p.apply(null, arguments);
            return e.type = "POST", t.ajax(e)
        }, t.getJSON = function() {
            var e = p.apply(null, arguments);
            return e.dataType = "json", t.ajax(e)
        }, t.fn.load = function(e, n, r) {
            if (!this.length) return this;
            var i, o = this,
                s = e.split(/\s/),
                a = p(e, n, r),
                u = a.success;
            return s.length > 1 && (a.url = s[0], i = s[1]), a.success = function(e) {
                o.html(i ? t("<div>").html(e.replace(x, "")).find(i) : e), u && u.apply(o, arguments)
            }, t.ajax(a), this
        };
        var S = encodeURIComponent;
        t.param = function(e, n) {
            var r = [];
            return r.add = function(e, n) {
                t.isFunction(n) && (n = n()), null == n && (n = ""), this.push(S(e) + "=" + S(n))
            }, d(r, e, n), r.join("&").replace(/%20/g, "+")
        }
    }(Zepto),
    function(t) {
        function e(t) {
            return t._zid || (t._zid = h++)
        }

        function n(t, n, o, s) {
            if (n = r(n), n.ns) var a = i(n.ns);
            return (g[e(t)] || []).filter(function(t) {
                return !(!t || n.e && t.e != n.e || n.ns && !a.test(t.ns) || o && e(t.fn) !== e(o) || s && t.sel != s)
            })
        }

        function r(t) {
            var e = ("" + t).split(".");
            return {
                e: e[0],
                ns: e.slice(1).sort().join(" ")
            }
        }

        function i(t) {
            return new RegExp("(?:^| )" + t.replace(" ", " .* ?") + "(?: |$)")
        }

        function o(t, e) {
            return t.del && !y && t.e in x || !!e
        }

        function s(t) {
            return b[t] || y && x[t] || t
        }

        function a(n, i, a, u, l, h, p) {
            var d = e(n),
                m = g[d] || (g[d] = []);
            i.split(/\s/).forEach(function(e) {
                if ("ready" == e) return t(document).ready(a);
                var i = r(e);
                i.fn = a, i.sel = l, i.e in b && (a = function(e) {
                    var n = e.relatedTarget;
                    return !n || n !== this && !t.contains(this, n) ? i.fn.apply(this, arguments) : void 0
                }), i.del = h;
                var d = h || a;
                i.proxy = function(t) {
                    if (t = c(t), !t.isImmediatePropagationStopped()) {
                        t.data = u;
                        var e = d.apply(n, t._args == f ? [t] : [t].concat(t._args));
                        return e === !1 && (t.preventDefault(), t.stopPropagation()), e
                    }
                }, i.i = m.length, m.push(i), "addEventListener" in n && n.addEventListener(s(i.e), i.proxy, o(i, p))
            })
        }

        function u(t, r, i, a, u) {
            var c = e(t);
            (r || "").split(/\s/).forEach(function(e) {
                n(t, e, i, a).forEach(function(e) {
                    delete g[c][e.i], "removeEventListener" in t && t.removeEventListener(s(e.e), e.proxy, o(e, u))
                })
            })
        }

        function c(e, n) {
            return (n || !e.isDefaultPrevented) && (n || (n = e), t.each(T, function(t, r) {
                var i = n[t];
                e[t] = function() {
                    return this[r] = w, i && i.apply(n, arguments)
                }, e[r] = E
            }), (n.defaultPrevented !== f ? n.defaultPrevented : "returnValue" in n ? n.returnValue === !1 : n.getPreventDefault && n.getPreventDefault()) && (e.isDefaultPrevented = w)), e
        }

        function l(t) {
            var e, n = {
                originalEvent: t
            };
            for (e in t) j.test(e) || t[e] === f || (n[e] = t[e]);
            return c(n, t)
        }
        var f, h = 1,
            p = Array.prototype.slice,
            d = t.isFunction,
            m = function(t) {
                return "string" == typeof t
            },
            g = {},
            v = {},
            y = "onfocusin" in window,
            x = {
                focus: "focusin",
                blur: "focusout"
            },
            b = {
                mouseenter: "mouseover",
                mouseleave: "mouseout"
            };
        v.click = v.mousedown = v.mouseup = v.mousemove = "MouseEvents", t.event = {
            add: a,
            remove: u
        }, t.proxy = function(n, r) {
            var i = 2 in arguments && p.call(arguments, 2);
            if (d(n)) {
                var o = function() {
                    return n.apply(r, i ? i.concat(p.call(arguments)) : arguments)
                };
                return o._zid = e(n), o
            }
            if (m(r)) return i ? (i.unshift(n[r], n), t.proxy.apply(null, i)) : t.proxy(n[r], n);
            throw new TypeError("expected function")
        }, t.fn.bind = function(t, e, n) {
            return this.on(t, e, n)
        }, t.fn.unbind = function(t, e) {
            return this.off(t, e)
        }, t.fn.one = function(t, e, n, r) {
            return this.on(t, e, n, r, 1)
        };
        var w = function() {
                return !0
            },
            E = function() {
                return !1
            },
            j = /^([A-Z]|returnValue$|layer[XY]$)/,
            T = {
                preventDefault: "isDefaultPrevented",
                stopImmediatePropagation: "isImmediatePropagationStopped",
                stopPropagation: "isPropagationStopped"
            };
        t.fn.delegate = function(t, e, n) {
            return this.on(e, t, n)
        }, t.fn.undelegate = function(t, e, n) {
            return this.off(e, t, n)
        }, t.fn.live = function(e, n) {
            return t(document.body).delegate(this.selector, e, n), this
        }, t.fn.die = function(e, n) {
            return t(document.body).undelegate(this.selector, e, n), this
        }, t.fn.on = function(e, n, r, i, o) {
            var s, c, h = this;
            return e && !m(e) ? (t.each(e, function(t, e) {
                h.on(t, n, r, e, o)
            }), h) : (m(n) || d(i) || i === !1 || (i = r, r = n, n = f), (i === f || r === !1) && (i = r, r = f), i === !1 && (i = E), h.each(function(f, h) {
                o && (s = function(t) {
                    return u(h, t.type, i), i.apply(this, arguments)
                }), n && (c = function(e) {
                    var r, o = t(e.target).closest(n, h).get(0);
                    return o && o !== h ? (r = t.extend(l(e), {
                        currentTarget: o,
                        liveFired: h
                    }), (s || i).apply(o, [r].concat(p.call(arguments, 1)))) : void 0
                }), a(h, e, i, r, n, c || s)
            }))
        }, t.fn.off = function(e, n, r) {
            var i = this;
            return e && !m(e) ? (t.each(e, function(t, e) {
                i.off(t, n, e)
            }), i) : (m(n) || d(r) || r === !1 || (r = n, n = f), r === !1 && (r = E), i.each(function() {
                u(this, e, r, n)
            }))
        }, t.fn.trigger = function(e, n) {
            return e = m(e) || t.isPlainObject(e) ? t.Event(e) : c(e), e._args = n, this.each(function() {
                e.type in x && "function" == typeof this[e.type] ? this[e.type]() : "dispatchEvent" in this ? this.dispatchEvent(e) : t(this).triggerHandler(e, n)
            })
        }, t.fn.triggerHandler = function(e, r) {
            var i, o;
            return this.each(function(s, a) {
                i = l(m(e) ? t.Event(e) : e), i._args = r, i.target = a, t.each(n(a, e.type || e), function(t, e) {
                    return o = e.proxy(i), i.isImmediatePropagationStopped() ? !1 : void 0
                })
            }), o
        }, "focusin focusout focus blur load resize scroll unload click dblclick mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave change select keydown keypress keyup error".split(" ").forEach(function(e) {
            t.fn[e] = function(t) {
                return 0 in arguments ? this.bind(e, t) : this.trigger(e)
            }
        }), t.Event = function(t, e) {
            m(t) || (e = t, t = e.type);
            var n = document.createEvent(v[t] || "Events"),
                r = !0;
            if (e)
                for (var i in e) "bubbles" == i ? r = !!e[i] : n[i] = e[i];
            return n.initEvent(t, r, !0), c(n)
        }
    }(Zepto),
    function(t) {
        t.fn.serializeArray = function() {
            var e, n, r = [],
                i = function(t) {
                    return t.forEach ? t.forEach(i) : void r.push({
                        name: e,
                        value: t
                    })
                };
            return this[0] && t.each(this[0].elements, function(r, o) {
                n = o.type, e = o.name, e && "fieldset" != o.nodeName.toLowerCase() && !o.disabled && "submit" != n && "reset" != n && "button" != n && "file" != n && ("radio" != n && "checkbox" != n || o.checked) && i(t(o).val())
            }), r
        }, t.fn.serialize = function() {
            var t = [];
            return this.serializeArray().forEach(function(e) {
                t.push(encodeURIComponent(e.name) + "=" + encodeURIComponent(e.value))
            }), t.join("&")
        }, t.fn.submit = function(e) {
            if (0 in arguments) this.bind("submit", e);
            else if (this.length) {
                var n = t.Event("submit");
                this.eq(0).trigger(n), n.isDefaultPrevented() || this.get(0).submit()
            }
            return this
        }
    }(Zepto),
    function(t, e) {
        function n(t) {
            return t.replace(/([a-z])([A-Z])/, "$1-$2").toLowerCase()
        }

        function r(t) {
            return i ? i + t : t.toLowerCase()
        }
        var i, o, s, a, u, c, l, f, h, p, d = "",
            m = {
                Webkit: "webkit",
                Moz: "",
                O: "o"
            },
            g = document.createElement("div"),
            v = /^((translate|rotate|scale)(X|Y|Z|3d)?|matrix(3d)?|perspective|skew(X|Y)?)$/i,
            y = {};
        t.each(m, function(t, n) {
            return g.style[t + "TransitionProperty"] !== e ? (d = "-" + t.toLowerCase() + "-", i = n, !1) : void 0
        }), o = d + "transform", y[s = d + "transition-property"] = y[a = d + "transition-duration"] = y[c = d + "transition-delay"] = y[u = d + "transition-timing-function"] = y[l = d + "animation-name"] = y[f = d + "animation-duration"] = y[p = d + "animation-delay"] = y[h = d + "animation-timing-function"] = "", t.fx = {
            off: i === e && g.style.transitionProperty === e,
            speeds: {
                _default: 400,
                fast: 200,
                slow: 600
            },
            cssPrefix: d,
            transitionEnd: r("TransitionEnd"),
            animationEnd: r("AnimationEnd")
        }, t.fn.animate = function(n, r, i, o, s) {
            return t.isFunction(r) && (o = r, i = e, r = e), t.isFunction(i) && (o = i, i = e), t.isPlainObject(r) && (i = r.easing, o = r.complete, s = r.delay, r = r.duration), r && (r = ("number" == typeof r ? r : t.fx.speeds[r] || t.fx.speeds._default) / 1e3), s && (s = parseFloat(s) / 1e3), this.anim(n, r, i, o, s)
        }, t.fn.anim = function(r, i, d, m, g) {
            var x, b, w, E = {},
                j = "",
                T = this,
                C = t.fx.transitionEnd,
                S = !1;
            if (i === e && (i = t.fx.speeds._default / 1e3), g === e && (g = 0), t.fx.off && (i = 0), "string" == typeof r) E[l] = r, E[f] = i + "s", E[p] = g + "s", E[h] = d || "linear", C = t.fx.animationEnd;
            else {
                b = [];
                for (x in r) v.test(x) ? j += x + "(" + r[x] + ") " : (E[x] = r[x], b.push(n(x)));
                j && (E[o] = j, b.push(o)), i > 0 && "object" == typeof r && (E[s] = b.join(", "), E[a] = i + "s", E[c] = g + "s", E[u] = d || "linear")
            }
            return w = function(e) {
                if ("undefined" != typeof e) {
                    if (e.target !== e.currentTarget) return;
                    t(e.target).unbind(C, w)
                } else t(this).unbind(C, w);
                S = !0, t(this).css(y), m && m.call(this)
            }, i > 0 && (this.bind(C, w), setTimeout(function() {
                S || w.call(T)
            }, 1e3 * (i + g) + 25)), this.size() && this.get(0).clientLeft, this.css(E), 0 >= i && setTimeout(function() {
                T.each(function() {
                    w.call(this)
                })
            }, 0), this
        }, g = null
    }(Zepto),
    function(t, e) {
        function n(n, r, i, o, s) {
            "function" != typeof r || s || (s = r, r = e);
            var a = {
                opacity: i
            };
            return o && (a.scale = o, n.css(t.fx.cssPrefix + "transform-origin", "0 0")), n.animate(a, r, null, s)
        }

        function r(e, r, i, o) {
            return n(e, r, 0, i, function() {
                s.call(t(this)), o && o.call(this)
            })
        }
        var i = window.document,
            o = (i.documentElement, t.fn.show),
            s = t.fn.hide,
            a = t.fn.toggle;
        t.fn.show = function(t, r) {
            return o.call(this), t === e ? t = 0 : this.css("opacity", 0), n(this, t, 1, "1,1", r)
        }, t.fn.hide = function(t, n) {
            return t === e ? s.call(this) : r(this, t, "0,0", n)
        }, t.fn.toggle = function(n, r) {
            return n === e || "boolean" == typeof n ? a.call(this, n) : this.each(function() {
                var e = t(this);
                e["none" == e.css("display") ? "show" : "hide"](n, r)
            })
        }, t.fn.fadeTo = function(t, e, r) {
            return n(this, t, e, null, r)
        }, t.fn.fadeIn = function(t, e) {
            var n = this.css("opacity");
            return n > 0 ? this.css("opacity", 0) : n = 1, o.call(this).fadeTo(t, n, e)
        }, t.fn.fadeOut = function(t, e) {
            return r(this, t, null, e)
        }, t.fn.fadeToggle = function(e, n) {
            return this.each(function() {
                var r = t(this);
                r[0 == r.css("opacity") || "none" == r.css("display") ? "fadeIn" : "fadeOut"](e, n)
            })
        }
    }(Zepto),
    function() {
        try {
            getComputedStyle(void 0)
        } catch (t) {
            var e = getComputedStyle;
            window.getComputedStyle = function(t) {
                try {
                    return e(t)
                } catch (n) {
                    return null
                }
            }
        }
    }();