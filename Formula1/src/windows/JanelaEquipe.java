package windows;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import entities.Equipe;
import windows.customclasses.TabelaNaoEditavel;

public class JanelaEquipe {
	private JFrame janelaEquipe;
	private JTextField jTextId;
	private JTextField jTextNome;
	private JTextField jTextNacionalidade;
	private JTextField jTextChefeEquipe;
	private JButton botaoConsultar;
	private JButton botaoGravar;
	private JButton botaoLimpar;
	private JButton botaoApagar;
	private TabelaNaoEditavel tableModel;
	private JTable tabela;

	public JFrame criarJanelaEquipe() {
		// Define a janela
		janelaEquipe = new JFrame("Atualização de equipe"); // Janela Normal
		janelaEquipe.setResizable(false); // A janela não poderá ter o tamanho ajustado
		janelaEquipe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaEquipe.setSize(500, 450); // Define tamanho da janela
		janelaEquipe.setLocationRelativeTo(null);
		// Define o layout da janela
		Container caixa = janelaEquipe.getContentPane();
		caixa.setLayout(null);
		// Define os labels dos campos
		JLabel labelId = new JLabel("ID: ");
		JLabel labelNome = new JLabel("Nome: ");
		JLabel labelNacionalidade = new JLabel("Nacionalidade: ");
		JLabel labelChefeEquipe = new JLabel("Chefe de Equipe: ");
		JLabel labelPilotos = new JLabel("Pilotos: ");
		// Posiciona os labels na janela
		labelId.setBounds(50, 40, 100, 20); // coluna, linha, largura, tamanho
		labelNome.setBounds(50, 80, 100, 20);
		labelNacionalidade.setBounds(50, 120, 100, 20);
		labelChefeEquipe.setBounds(50, 160, 100, 20);
		labelPilotos.setBounds(50, 200, 100, 20);
		// Define os input box
		jTextId = new JTextField();
		jTextNome = new JTextField();
		jTextNacionalidade = new JTextField();
		jTextChefeEquipe = new JTextField();
		// Define se os campos estão habilitados ou não no início
		jTextId.setEnabled(true);
		jTextNome.setEnabled(false);
		jTextNacionalidade.setEnabled(false);
		jTextChefeEquipe.setEnabled(false);
		// Posiciona os input box
		jTextId.setBounds(180, 40, 50, 20);
		jTextNome.setBounds(180, 80, 150, 20);
		jTextNacionalidade.setBounds(180, 120, 150, 20);
		jTextChefeEquipe.setBounds(180, 160, 150, 20);
		// Adiciona os rótulos e os input box na janela
		janelaEquipe.add(labelId);
		janelaEquipe.add(labelNome);
		janelaEquipe.add(labelNacionalidade);
		janelaEquipe.add(labelChefeEquipe);
		janelaEquipe.add(labelPilotos);
		janelaEquipe.add(jTextId);
		janelaEquipe.add(jTextNome);
		janelaEquipe.add(jTextNacionalidade);
		janelaEquipe.add(jTextChefeEquipe);
		// Define botões e a localização deles na janela
		botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(230, 40, 100, 20);
		janelaEquipe.add(botaoConsultar);
		botaoGravar = new JButton("Gravar");
		botaoGravar.setBounds(50, 330, 100, 20);
		botaoGravar.setEnabled(false);
		janelaEquipe.add(botaoGravar);
		botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(350, 330, 100, 20);
		janelaEquipe.add(botaoLimpar);
		botaoApagar = new JButton("Apagar");
		botaoApagar.setBounds(200, 330, 100, 20);
		botaoApagar.setEnabled(false);
		janelaEquipe.add(botaoApagar);
		// Define a tabela de pilotos da equipe
		tableModel = new TabelaNaoEditavel();
		tabela = new JTable(tableModel);
		tabela.setRowSelectionAllowed(false);
		tableModel.addColumn("Nome");
		tableModel.addColumn("Nacionalidade");
		tableModel.addColumn("Nº Carro");
		JScrollPane painelTabela = new JScrollPane(tabela);
		painelTabela.setBounds(50, 230, 400, 71);
		janelaEquipe.add(painelTabela);
		// Define objeto equipe para pesquisar no banco de dados
		Equipe equipe = new Equipe();
		// Define ações dos botões
		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(jTextId.getText());
					botaoGravar.setEnabled(true);
					String nome, nacionalidade, chefeEquipe;
					if (!equipe.consultarEquipe(id)) {
						JOptionPane.showMessageDialog(janelaEquipe, "Equipe não cadastrada.");
						nome = "";
						nacionalidade = "";
						chefeEquipe = "";
						botaoApagar.setEnabled(false);
						jTextNome.requestFocus();
					} else {
						nome = equipe.getNome();
						nacionalidade = equipe.getNacionalidade();
						chefeEquipe = equipe.getChefe();
						botaoApagar.setEnabled(true);
						botaoGravar.setText("Alterar");
						for (Object[] piloto : equipe.getListaPilotos(id)) {
							tableModel.addRow(piloto);
						}
					}
					jTextNome.setEnabled(true);
					jTextId.setEnabled(false);
					jTextNacionalidade.setEnabled(true);
					jTextChefeEquipe.setEnabled(true);
					jTextNome.setText(nome);
					jTextNacionalidade.setText(nacionalidade);
					jTextChefeEquipe.setText(chefeEquipe);
					botaoConsultar.setEnabled(false);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janelaEquipe, "Preencha os campos corretamente!!");
				}
			}
		});

		botaoGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(janelaEquipe, "Deseja atualizar?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					int id = Integer.parseInt(jTextId.getText());
					String nome = jTextNome.getText().trim(); // Retira os espaços em branco
					String nacionalidade = jTextNacionalidade.getText().trim(); // Retira os espaços em branco
					String chefeEquipe = jTextChefeEquipe.getText().trim(); // Retira os espaços em branco
					if (nome.length() == 0) {
						JOptionPane.showMessageDialog(janelaEquipe, "Preencha o campo nome");
						jTextNome.requestFocus();
					} else if (nacionalidade.length() == 0) {
						JOptionPane.showMessageDialog(janelaEquipe, "Preencha o campo nacionalidade");
						jTextNacionalidade.requestFocus();
					} else if (chefeEquipe.length() == 0) {
						JOptionPane.showMessageDialog(janelaEquipe, "Preencha o campo chefe");
						jTextNacionalidade.requestFocus();
					} else {
						if (!equipe.consultarEquipe(id)) {
							if (!equipe.cadastrarEquipe(id, nome, nacionalidade, chefeEquipe)) {
								JOptionPane.showMessageDialog(janelaEquipe, "Erro no cadastro da equipe!");
							} else {
								JOptionPane.showMessageDialog(janelaEquipe, "Cadastro realizado!");
							}
						} else {
							if (!equipe.atualizarEquipe(id, nome, nacionalidade, chefeEquipe)) {
								JOptionPane.showMessageDialog(janelaEquipe, "Erro na atualização da equipe!");
							} else {
								JOptionPane.showMessageDialog(janelaEquipe, "Atualização realizada!");
							}
						}
					}
				}
				// restaura a janela
				resetJanela();
			}
		});

		botaoApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(jTextId.getText());
				// Verifica se a equipe existe
				if (equipe.consultarEquipe(id)) {
					int confirmacao = JOptionPane.showConfirmDialog(janelaEquipe, "Tem certeza?", "Apagar equipe",
							JOptionPane.YES_NO_OPTION);
					if (confirmacao == JOptionPane.YES_OPTION) {
						// Apaga a equipe da tabela
						if (!equipe.apagarEquipe(id)) {
							JOptionPane.showMessageDialog(janelaEquipe,
									"Para apagar a equipe, apague os pilotos primeiro!");
						} else {
							JOptionPane.showMessageDialog(janelaEquipe, "Equipe apagada da tabela!");
						}
						// restaura a janela
						resetJanela();
					}
				} else {
					JOptionPane.showMessageDialog(janelaEquipe, "Equipe não encontrada na tabela!");
				}
			}
		});

		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetJanela();
			}
		});
		return janelaEquipe;
	}

	// reseta a janela para as condições originais
	public void resetJanela() {
		jTextId.setText(""); // Limpar campo
		jTextNome.setText(""); // Limpar campo
		jTextNacionalidade.setText(""); // Limpar campo
		jTextChefeEquipe.setText(""); // Limpar campo
		jTextId.setEnabled(true);
		jTextNome.setEnabled(false);
		jTextNacionalidade.setEnabled(false);
		jTextChefeEquipe.setEnabled(false);
		botaoConsultar.setEnabled(true);
		botaoGravar.setEnabled(false);
		botaoLimpar.setEnabled(true);
		botaoApagar.setEnabled(false);
		jTextId.requestFocus(); // Colocar o foco em um campo
		botaoGravar.setText("Gravar");
		DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
		dtm.setRowCount(0);
	}
}