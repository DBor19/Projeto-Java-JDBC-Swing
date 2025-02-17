package windows;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import entities.Corrida;
import entities.Equipe;
import entities.Piloto;

public class JanelaCorrida {
	private JFrame janelaCorrida;
	private JTextField jTextId;
	private JTextField jTextNome;
	private JTextField jTextCircuito;
	JComboBox<String> comboboxVencedor;
	JTextField jTextVoltas;
	JTextField jTextEquipeVencedora;
	private JButton botaoConsultar;
	private JButton botaoGravar;
	private JButton botaoLimpar;
	private JButton botaoApagar;
	private JButton botaoAtualizarPiloto;

	public JFrame criarJanelaCorrida() {
		// Define a janela
		janelaCorrida = new JFrame("Atualização de corrida"); // Janela Normal
		janelaCorrida.setResizable(false); // A janela não poderá ter o tamanho ajustado
		janelaCorrida.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaCorrida.setSize(500, 400); // Define tamanho da janela
		janelaCorrida.setLocationRelativeTo(null);
		// Define o layout da janela
		Container caixa = janelaCorrida.getContentPane();
		caixa.setLayout(null);
		// Define os labels dos campos
		JLabel labelId = new JLabel("ID: ");
		JLabel labelNome = new JLabel("Nome: ");
		JLabel labelCircuito = new JLabel("Circuito: ");
		JLabel labelVoltas = new JLabel("Voltas: ");
		JLabel labelVencedorNome = new JLabel("Vencedor: ");
		JLabel labelEquipeVencedora = new JLabel("Equipe: ");
		// Posiciona os labels na janela
		labelId.setBounds(50, 40, 100, 20); // coluna, linha, largura, tamanho
		labelNome.setBounds(50, 80, 100, 20);
		labelCircuito.setBounds(50, 120, 100, 20);
		labelVoltas.setBounds(50, 160, 100, 20);
		labelVencedorNome.setBounds(50, 200, 100, 20);
		labelEquipeVencedora.setBounds(50, 240, 100, 20);
		// Define os input box
		jTextId = new JTextField();
		jTextNome = new JTextField();
		jTextCircuito = new JTextField();
		comboboxVencedor = new JComboBox<String>();
		jTextVoltas = new JTextField();
		jTextEquipeVencedora = new JTextField();
		// Define se os campos estão habilitados ou não no início
		jTextId.setEnabled(true);
		jTextNome.setEnabled(false);
		jTextCircuito.setEnabled(false);
		comboboxVencedor.setEnabled(false);
		jTextVoltas.setEnabled(false);
		jTextEquipeVencedora.setEnabled(false);
		// Posiciona os input box
		jTextId.setBounds(180, 40, 70, 20);
		jTextNome.setBounds(180, 80, 170, 20);
		jTextCircuito.setBounds(180, 120, 200, 20);
		jTextVoltas.setBounds(180, 160, 70, 20);
		comboboxVencedor.setBounds(180, 200, 170, 20);
		jTextEquipeVencedora.setBounds(180, 240, 170, 20);
		jTextId.setDisabledTextColor(Color.DARK_GRAY);
		jTextNome.setDisabledTextColor(Color.DARK_GRAY);
		jTextCircuito.setDisabledTextColor(Color.DARK_GRAY);
		jTextVoltas.setDisabledTextColor(Color.DARK_GRAY);
		jTextEquipeVencedora.setDisabledTextColor(Color.DARK_GRAY);
		// Adiciona os rótulos e os input box na janela
		janelaCorrida.add(labelId);
		janelaCorrida.add(labelNome);
		janelaCorrida.add(labelCircuito);
		janelaCorrida.add(labelVoltas);
		janelaCorrida.add(labelVencedorNome);
		janelaCorrida.add(labelEquipeVencedora);
		janelaCorrida.add(jTextId);
		janelaCorrida.add(jTextNome);
		janelaCorrida.add(jTextCircuito);
		janelaCorrida.add(comboboxVencedor);
		janelaCorrida.add(jTextVoltas);
		janelaCorrida.add(jTextEquipeVencedora);
		// Define botões e a localização deles na janela
		botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(250, 40, 100, 20);
		janelaCorrida.add(botaoConsultar);
		botaoGravar = new JButton("Gravar");
		botaoGravar.setBounds(50, 300, 100, 20);
		botaoGravar.setEnabled(false);
		janelaCorrida.add(botaoGravar);
		botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(350, 300, 100, 20);
		janelaCorrida.add(botaoLimpar);
		botaoApagar = new JButton("Apagar");
		botaoApagar.setBounds(200, 300, 100, 20);
		botaoApagar.setEnabled(false);
		janelaCorrida.add(botaoApagar);
		botaoAtualizarPiloto = new JButton("Atualizar Piloto");
		botaoAtualizarPiloto.setBounds(50, 200, 130, 20);
		botaoAtualizarPiloto.setVisible(false);
		botaoAtualizarPiloto.setEnabled(false);
		janelaCorrida.add(botaoAtualizarPiloto);
		// Define objeto corrida para pesquisar no banco de dados
		Corrida corrida = new Corrida();
		// Define ações dos botões
		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Piloto piloto = new Piloto();
					int id = Integer.parseInt(jTextId.getText());
					botaoGravar.setEnabled(true);
					String nome, circuito, vencedorNome = null, equipeNome = null;
					int vencedorId, voltas;
					if (!corrida.consultarCorrida(id)) { // caso a corrida nao exista na tabela, cria a janela zerada
						JOptionPane.showMessageDialog(janelaCorrida, "Corrida não cadastrada.");
						nome = "";
						circuito = "";
						vencedorId = 0;
						voltas = 0;
						vencedorNome = "";
						equipeNome = "";
						jTextId.setEnabled(false);
						jTextCircuito.setEnabled(true);
						botaoApagar.setEnabled(false);
						jTextNome.setEnabled(true);
						jTextNome.requestFocus();
						ativarCombobox();
					// cria a janela com informações da tabela
					} else {
						Equipe equipe = new Equipe();
						int equipeId;
						nome = corrida.getNome();
						circuito = corrida.getCircuito();
						vencedorId = corrida.getVencedor();
						voltas = corrida.getVoltas();
						if (piloto.consultarPiloto(vencedorId)) {
							vencedorNome = piloto.getNome();
							equipeId = piloto.getIdEquipe();
							if (equipe.consultarEquipe(equipeId)) {
								equipeNome = equipe.getNome();
							}
						}
						labelVencedorNome.setVisible(false);
						botaoAtualizarPiloto.setVisible(true);
						botaoAtualizarPiloto.setEnabled(true);
						jTextId.setEnabled(false);
						jTextCircuito.setEnabled(false);
						jTextVoltas.setEnabled(true);
						botaoApagar.setEnabled(true);
						comboboxVencedor.addItem(vencedorNome);
						comboboxVencedor.setEnabled(false);
					}
					jTextNome.setText(nome);
					jTextCircuito.setText(circuito);
					jTextEquipeVencedora.setText(equipeNome);
					jTextVoltas.setText(Integer.toString(voltas));
					botaoConsultar.setEnabled(false);

				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janelaCorrida, "Preencha os campos corretamente!!" + erro.toString());
				}
			}
		});

		botaoGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int resposta = JOptionPane.showConfirmDialog(janelaCorrida, "Deseja atualizar?", "Confirmação",
							JOptionPane.YES_NO_OPTION);
					if (resposta == JOptionPane.YES_OPTION) {
						Piloto piloto = new Piloto();
						int id, voltas;
						try {
							id = Integer.parseInt(jTextId.getText());
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(janelaCorrida, "ID inválido! Insira um número válido.");
							return;
						}
						String nome = jTextNome.getText().trim(); // Retira os espaços em branco
						String circuito = jTextCircuito.getText().trim(); // Retira os espaços em branco
						voltas = Integer.parseInt(jTextVoltas.getText().trim());
						int vencedorId;
						String nomePiloto = (String) comboboxVencedor.getSelectedItem();
						if (nomePiloto == null) {
							JOptionPane.showMessageDialog(janelaCorrida,
									"Não é possível cadastrar a corrida pois não há pilotos cadastrados!");
							return;
						} else {
							try {
								vencedorId = piloto.getIdByName(nomePiloto);
							} catch (ClassCastException ex) {
								JOptionPane.showMessageDialog(janelaCorrida, "Erro ao obter o ID do piloto vencedor!");
								return;
							}
						}
						if (nome.length() == 0) {
							JOptionPane.showMessageDialog(janelaCorrida, "Preencha o campo nome");
							jTextNome.requestFocus();
						} else if (circuito.length() == 0) {
							JOptionPane.showMessageDialog(janelaCorrida, "Preencha o campo circuito");
							jTextCircuito.requestFocus();
						} else {
							if (!corrida.consultarCorrida(id)) {
								if (!corrida.cadastrarCorrida(id, nome, circuito, voltas, vencedorId)) {
									JOptionPane.showMessageDialog(janelaCorrida, "Erro no cadastro da corrida!");
								} else {
									JOptionPane.showMessageDialog(janelaCorrida, "Cadastro realizado!");
								}
							} else {
								if (!corrida.atualizarCorrida(id, voltas, vencedorId)) {
									JOptionPane.showMessageDialog(janelaCorrida,
											"Erro na atualização da corrida! O piloto vencedor precisa estar cadastrado!");
								} else {
									JOptionPane.showMessageDialog(janelaCorrida, "Atualização realizada!");
								}
							}
						}
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(janelaCorrida, "Erro inesperado: " + ex.getMessage());
				} finally {
					resetarJanela();
					labelVencedorNome.setVisible(true);
				}
			}
		});

		botaoApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(jTextId.getText());
				// Verifica se a corrida existe
				if (corrida.consultarCorrida(id)) {
					int confirmacao = JOptionPane.showConfirmDialog(janelaCorrida, "Tem certeza?", "Apagar corrida",
							JOptionPane.YES_NO_OPTION);
					if (confirmacao == JOptionPane.YES_OPTION) {
						// Apaga a corrida da tabela
						corrida.apagarCorrida(id);
						JOptionPane.showMessageDialog(janelaCorrida, "Corrida apagada da tabela!");
						resetarJanela();
						labelVencedorNome.setVisible(true);
					}
				} else {
					JOptionPane.showMessageDialog(janelaCorrida, "Corrida não encontrada na tabela!");
				}
			}
		});

		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetarJanela();
				labelVencedorNome.setVisible(true);
			}
		});
		
		botaoAtualizarPiloto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ativarCombobox();
			}
		});
		return janelaCorrida;
	}

	// reseta a janela para as condições originais
	public void resetarJanela() {
		jTextId.setText(""); // Limpar campos
		jTextNome.setText("");
		jTextCircuito.setText("");
		comboboxVencedor.removeAllItems();
		jTextVoltas.setText("");
		jTextEquipeVencedora.setText("");
		jTextId.setEnabled(true);
		jTextNome.setEnabled(false);
		jTextCircuito.setEnabled(false);
		comboboxVencedor.setEnabled(false);
		botaoConsultar.setEnabled(true);
		botaoGravar.setEnabled(false);
		botaoLimpar.setEnabled(true);
		botaoApagar.setEnabled(false);
		botaoAtualizarPiloto.setVisible(false);
		botaoAtualizarPiloto.setEnabled(false);
		jTextId.requestFocus(); // Colocar o foco em um campo
		botaoGravar.setBounds(50, 300, 100, 20);
		botaoGravar.setText("Gravar");
	}
	
	public void ativarCombobox() {
		comboboxVencedor.removeAllItems();
		List<String> listaEquipes = new ArrayList<>();
		Piloto piloto = new Piloto();
		for (String i : piloto.getNameList()) {
			listaEquipes.add(i);
		}
		for (String item : listaEquipes) {
			comboboxVencedor.addItem(item);
		}
		comboboxVencedor.setEnabled(true);
	}
}