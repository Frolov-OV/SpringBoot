$(function(){

    const appendToDo = function(data){
        var toDoCode = '<a href="#" class="toDo-link" data-id="' +
            data.id + '">' + data.name + '</a><br>';
        $('#toDo-list')
            .append('<div>' + toDoCode + '</div>');
    };

    $('#show-add-toDo-form').click(function(){
        $('#toDo-form').css('display', 'flex');
    });

    $('#toDo-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    $(document).on('click', '.toDo-link', function(){
        var link = $(this);
        var toDoId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/toDos/' + toDoId,
            success: function(response)
            {
                var code = '<span>Срок выполнения:' + response.year + '</span>';
                link.parent().append(code);
            },
            error: function(response)
            {
                if(response.status == 404) {
                    alert('Задача не найдена!');
                }
            }
        });
        return false;
    });

    $('#save-toDo').click(function()
    {
        var data = $('#toDo-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/toDos/',
            data: data,
            success: function(response)
            {
                $('#toDo-form').css('display', 'none');
                var toDo = {};
                toDo.id = response;
                var dataArray = $('#toDo-form form').serializeArray();
                for(i in dataArray) {
                    toDo[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendtoDo(toDo);
            }
        });
        return false;
    });

});
