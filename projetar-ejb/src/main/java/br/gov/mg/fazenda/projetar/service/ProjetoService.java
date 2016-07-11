package br.gov.mg.fazenda.projetar.service;

import java.sql.Date;
import java.util.List;

import br.gov.mg.fazenda.geral.anotacao.Servico;
import br.gov.mg.fazenda.geral.service.WriteableService;
import br.gov.mg.fazenda.projetar.entity.Fase;
import br.gov.mg.fazenda.projetar.entity.Projeto;
import br.gov.mg.fazenda.projetar.entity.TipoProjeto;
import br.gov.mg.fazenda.projetar.entity.Unidade;
import br.gov.mg.fazenda.projetar.entity.security.Usuario;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 10/04/2016
 */

@Servico
public interface ProjetoService extends WriteableService<Projeto,Long> {
	public List<Projeto> buscarPorAno( Date ano ); 
	public List<Projeto> buscarPorNome( String nome );
	public List<Projeto> buscarPorParteNome( String nome );
	public List<Projeto> buscarPorGerente( Usuario usuario );
	public List<Projeto> buscarPorInterlocutor( Usuario usuario );
	public List<Projeto> buscarPorUnidade( Unidade unidade );
	public List<Projeto> buscarPorUnidades( List<Unidade> unidades );
	public List<Projeto> buscarPorTipoProjeto( TipoProjeto tipoProjeto );
	public List<Projeto> buscarPorTipoProjetoUnidade( TipoProjeto tipoProjeto , Unidade unidade );
	public List<Projeto> buscarPorTiposProjetosUnidade( List<TipoProjeto> tiposProjetos , Unidade unidade );
	public List<Projeto> buscarPorGerenteFase( Usuario usuario , Fase fase );
	public List<Projeto> buscarPorInterlocutorFase( Usuario usuario , Fase fase );
}
