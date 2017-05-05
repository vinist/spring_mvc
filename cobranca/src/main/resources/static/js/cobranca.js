$('#confirmacaoExclusao').on('show.bs.modal', function(event){
	
	var button = $(event.relatedTarget);
	
	var id = button.data('id');
	var descricao = button.data('descricao');
	
	
	var modal = $(this);
	
	var form = modal.find('form');
	var action = form.data('url-base');
	
	var href = modal.find('href');
	
	if(!action.endsWith('/')){
		action += '/';
	}
	form.attr('action', action + id)
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o título <strong>'+ descricao + '</strong>')
	
});


$(function() {
	$('[rel="tooltip"]').tooltip();
	$('.mask-money').maskMoney({decimal:',', thousands:'.', allowZero: true});
	
	$('.js-status-action').on('click', function(event) {
		event.preventDefault();
		
		var botaoReceber = $(event.currentTarget);
		var urlReceber = botaoReceber.attr('href');
		
		var response = $.ajax({
			url: urlReceber,
			type: 'PUT'
		});
		
		response.done(function(e){
			var id = botaoReceber.data('id');
			$('[data-row='+id+ ']').html('<span class="label label-success">'+e+'</span>');
			botaoReceber.hide();
		});
	
		response.fail(function(e){
			console.log(e);
			alert("Erro ao receber cobrança");
		});
	});
});

