<#import "parts/common.ftl" as c>

<@c.page>
User editor

<form action="/user" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="hidden" name="userId" value="${user.id}">

    <input type="text" name="username" value="${user.username}">
    <input type="text" name="password" value="${user.password}">
    <#list roles as role>
        <div>
            <label>
                <input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>
                ${role}
            </label>
        </div>
    </#list>
    <button type="submit">Save</button>
    <a type="button" href="/user">Cancel</a>
</form>


</@c.page>