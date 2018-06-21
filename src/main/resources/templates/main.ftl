<#import "parts/common.ftl" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form action="/main" method="get" class="form-inline">
                <input class="form-control" type="text" name="filter" value="${filter?ifExists}" placeholder="Input some tag">
                <button class="btn btn-primary ml-2" type="submit">Search</button>
            </form>
        </div>
    </div>

    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add new message
    </a>

    <div class="collapse <#if message??>show</#if> mt-3" id="collapseExample">
        <div class="form-group">
            <form method="post" enctype="multipart/form-data">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <div class="form-group">
                    <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                           value="<#if message??>${message.text}</#if>" name="text" placeholder="Введите сообщение" />
                    <#if textError??>
                        <div class="invalid-feedback">
                        ${textError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <input class="form-control" value="<#if message??>${message.tag}</#if>" type="text" name="tag" placeholder="Введите tag">
                    <#if tagError??>
                        <div class="invalid-feedback">
                        ${tagError}
                        </div>
                    </#if>
                </div>
                <div class="custom-file">
                    <input type="file" class="custom-file-input" name="file">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
                <div class="form-group mt-2">
                    <button class="btn btn-primary" type="submit">Send</button>
                </div>
            </form>
        </div>
    </div>

<div class="card-columns">
    <#list messages as message>
        <div class="card my-2" style="width: 18rem;">
            <#if message.filename??>
                <img class="card-img-top" src="/img/${message.filename}">
            </#if>
            <div class="card-body">
                <h5 class="card-title">${message.tag}</h5>
                <p class="card-text">${message.text}</p>
            </div>
            <div class="card-footer text-muted">
                ${message.user.username}
            </div>
        </div>
    <#else>
        <strong>List message is empty</strong>
    </#list>
</div>
</@c.page>