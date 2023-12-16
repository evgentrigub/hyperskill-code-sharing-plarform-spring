<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
</head>
<body>
<#setting number_format="computer">
<#list codes as code>
<div>
    <p><span id="load_date" >${code.date}</span></p>
    <#if code.time gt 0 >
    The code will e available for <span id="time_restriction">${code.time}</span> seconds <BR>
    </#if>
    <#if code.viewLimit >
    <span id="views_restriction">${code.views}</span>  more views allowed<BR>
    </#if>

    <pre id="code_snippet"><code>${code.code}</code></pre>
</div>
</#list>
<script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
<script>hljs.initHighlightingOnLoad();</script>
</body>
</html>