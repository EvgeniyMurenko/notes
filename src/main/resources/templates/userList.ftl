<#import "parts/common.ftl" as c>

<@c.page>
    User list


    <table>
        <thead>
            <tr>
                <th>Name</th>
                <th>Password</th>
                <th>Role</th>
                <th>Action</th>
            </tr>
        </thead>

        <tbody>
            <#list users as user>
                <tr>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>
                        <#list user.roles as role>
                            ${role}<#sep>,
                        </#list>
                    </td>
                    <td><a href="/user/${user.id}">Edit user </a></td>
                </tr>
            </#list>

        </tbody>

    </table>


</@c.page>