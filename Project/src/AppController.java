import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AppController implements Initializable  {

	
	public static ObservableList<User> list = FXCollections.observableArrayList();
	

	@FXML private TableView<User> table_view;
	
	//��ư
	@FXML private Button btn_add;
	@FXML private Button btn_delete;  
	@FXML private Button btn_search;
	@FXML private Button btn_update;
	
	//�˻�
	@FXML private TextField text_name;
	@FXML private TextField text_phone;
	@FXML private ChoiceBox<Object> choice_region; 
	@FXML private TextField text_address;
	@FXML private ChoiceBox<Object> choice_product;
	@FXML private TextField text_amount;
	
	//��ǰ�� ����
	@FXML private Label productA;
	@FXML private Label productB;
	@FXML private Label productC;
	
	//��ǰ ����
	int pdcA_amount;
	int pdcB_amount;
	int pdcC_amount;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
		//DB�� �ִ� �ڷḦ list�� ���� 
		AppStart.DB();
		
		//list����� TableView�� ���
		startTableView();
		
		//��ǰ�� ����
		product_Amount();
	    
		
		
		//choice_Box ���
		Choice_Box_region();
		Choice_Box_product();
	    
		
		
		
		
		//btnAdd ��ư
		btn_add.setOnAction(event->handleAddaction(event));
		//btnUpdate ��ư
		btn_update.setOnAction(event->handleUpdateAction(event));
		//btnDelect ��ư
		btn_delete.setOnAction(event->handleDelectAction(event));
		//btnSerch ��ư
		btn_search.setOnAction(event->handleSerchAction(event));
		
		//���͹�ư���� �˻�
		text_name.setOnAction(event->handleSerchAction(event));
	    text_phone.setOnAction(event->handleSerchAction(event));
	   
	}

	
	
	


	private void handleUpdateAction(ActionEvent event) {
		// TODO Auto-generated method stub
		
		
		//���̺� ���� ������ �� ���� ���̾� �α� �ȶ߰� 
		if(table_view.getSelectionModel().getSelectedIndex()!=-1) {
		
		//�������� ������Ʈ ����� �ƴ�. ������ ��ü �ҷ��帰�� ������ �����ع����� ���� ����� �������� ����.
		// TODO Auto-generated method stub
				try {
					
					
				
				//���̾� �α�â ���.
				Stage dialog = new Stage(StageStyle.UTILITY);
				dialog.initModality(Modality.WINDOW_MODAL);
				dialog.initOwner(btn_add.getScene().getWindow());  //�θ� 
				dialog.setTitle("����");
				
				
				Parent parent = FXMLLoader.load(getClass().getResource("app_add.fxml"));
				Scene scene = new Scene(parent);
				dialog.setScene(scene);
				dialog.setResizable(false);
				dialog.show();
				
				
				
				//���� ���̺��� ������ Ʃ�� ������  ���̺� ��ȭ��ȣ �̿��ؼ� ���� ���̺� ������.
				String name =table_view.getSelectionModel().getSelectedItem().getName();
				String phone =table_view.getSelectionModel().getSelectedItem().getPhone();
				String regin =table_view.getSelectionModel().getSelectedItem().getRegin();
				String address =table_view.getSelectionModel().getSelectedItem().getAddress();
				String product =table_view.getSelectionModel().getSelectedItem().getProduct();
				Integer amount = table_view.getSelectionModel().getSelectedItem().getAmount();
				String StringAmount = amount.toString();
				
				
				int num1 =0;  //���̽� �����ͼ� ���̾�α� ���� ����

				//if ������  ���� ���� �λ�  1 2 3 ������ �ؼ� ���̽� ���̾�α� �ʱⰪ���� �ҷ����̱�
				if(regin.toString().equals("����")) {
					num1=1;
				}else if(regin.toString().equals("����")){
					num1=2;
				}else {
					num1=3;
				}
				
				
				int num2 =0;  //���̽� �����ͼ� ���̾�α� ��ǰ ����
				//if ������  ��ǰA ��ǰB ��ǰC  1 2 3 ������ �ؼ� ���̽� ���̾�α� �ʱⰪ���� �ҷ����̱�
				
				if(product.toString().equals("��ǰA")) {
					num2=1;
				}else if(product.toString().equals("��ǰB")){
					num2=2;
				}else {
					num2=3;
				}
				
				
				
				//Table�ڷ� ���̾�α׷� ������ 
				//Main���̺��� ������ ���� ���̾�α׷� �ҷ�����
				TextField dialogTextName = (TextField)parent.lookup("#textName");
				dialogTextName.setText(name);
				TextField dialogTextPhone = (TextField)parent.lookup("#textPhone");
				dialogTextPhone.setText(phone);
				TextField dialogTextAddress = (TextField)parent.lookup("#textAddress");
				dialogTextAddress.setText(address);
				TextField dialogTextAmount = (TextField)parent.lookup("#textAmount");
				dialogTextAmount.setText(StringAmount);
				
				
				@SuppressWarnings("unchecked")
				ChoiceBox<Object> choiceRegin = (ChoiceBox<Object>)parent.lookup("#choiceRegin");
				choiceRegin.setItems(FXCollections.observableArrayList(
						"����", "����", "�λ�",new Separator(),"����"
						));
				
				choiceRegin.getSelectionModel().select(num1-1);
				
				//��ǰ ��� �߰�
				@SuppressWarnings("unchecked")
				ChoiceBox<Object> choiceProduct = (ChoiceBox<Object>)parent.lookup("#choiceProduct");
				choiceProduct.setItems(FXCollections.observableArrayList(
						"��ǰA", "��ǰB", "��ǰC",new Separator(),"����"
						));
				
				choiceProduct.getSelectionModel().select(num2-1);
				
				
		
	
				//���̾�α׿��� ��� ������ �پ� �α�â ����.
				Button btnCancel = (Button)parent.lookup("#dialog_close");
				btnCancel.setOnAction(e->dialog.close());
				
	
				//���̾�α׿��� Ȯ�� ��ư
				Button btnSave = (Button)parent.lookup("#dialog_save");
				
		
				btnSave.setOnAction(e->{
					try {
						
						
						//���� Ʃ�� ����.
						int selected =table_view.getSelectionModel().getSelectedIndex();
						
						
						TextField textname = (TextField)parent.lookup("#textName");
						TextField textphone = (TextField)parent.lookup("#textPhone");
						@SuppressWarnings({ "rawtypes", "unused" })
						ChoiceBox choiceregin = (ChoiceBox)parent.lookup("#choiceRegin");
						TextField textaddress = (TextField)parent.lookup("#textAddress");
						@SuppressWarnings("rawtypes")
						ChoiceBox choiceproduct = (ChoiceBox)parent.lookup("#choiceProduct");
						TextField textamount = (TextField)parent.lookup("#textAmount");
						
						
						
						String name1 = textname.getText();
						String phone1 = textphone.getText();
						String regin1 = (String) choiceRegin.getSelectionModel().getSelectedItem();
						String address1 = textaddress.getText();
						String product1 = (String) choiceproduct.getSelectionModel().getSelectedItem();
						String amount1 = textamount.getText();
						
						
						User user = new User(name1, phone1, regin1, address1, product1, Integer.parseInt(amount1));
						list.set(selected, user);  //������ ��Ʃ��Ʈ ��ü ����Ʈ�� ����
						//add�ϸ� �� �ڿ� list�� �߰���. set�ϸ� ������ ���� ���������°�.
						
						
						
						//���⼭ ���̽� ����Ʈ�� �о� �帲 �̰� ȭ�鿡 ��� DB�� �����ϸ��
						Class.forName("com.mysql.cj.jdbc.Driver");
						AppStart.conn = DriverManager.getConnection(
								"jdbc:mysql://localhost:3306/project?useSSL=false&serverTimezone=UTC","an", "a1641418");
					
						AppStart.stmt = AppStart.conn.createStatement();
						
						
						//������ �⺻Ű ���̺� ��ȭ��ȣ�� �̿��ؼ� ����
						//�ݵ�� ���� ����� ���� �����ؾߵ�. ������ �ڷ� �⺻Ű�� ������ ���¿��� ���ο� ������ �Է��ҷ��� �⺻Ű �ߺ����� �Է��� �ȵ�
						@SuppressWarnings("unused")
						int rs = AppStart.stmt.executeUpdate("delete from project where phone='"+phone+"'"); 
						rs = AppStart.stmt.executeUpdate("insert project(name, phone, regin, address, product, amount) values('"+name1+"', '"+phone1+"', "+"'"+regin1+"', '"+address1+"', '"+product1+"', '"+amount1+"')");   
						
						
						

		
						
						product_Amount();
						dialog.close();
						
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
					
					}
					
					 
				});
				
		
				//�������� Textâ���� ���� ������ Ȯ�ι�ư 
				dialogTextName.setOnAction(btnSave.getOnAction());
				dialogTextPhone.setOnAction(btnSave.getOnAction());
				dialogTextAddress.setOnAction(btnSave.getOnAction());
				dialogTextAmount.setOnAction(btnSave.getOnAction());
				
				
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
				
				//���� ��������
		}else {
			//���� �Ͻÿ� ���̾�α� ���� �ڵ� �ۼ�
			
			
			
		}
				
	
	}

	
	
	
	
	
	// �˻� ��ư Ŭ�� DB���� �˻��� TableView�� ���.
	@SuppressWarnings("unused")
	private void handleSerchAction(ActionEvent event) {
		// TODO Auto-generated method stub
		
		
		
		
		//table �������� �ʱ�ȭ  �� ȭ�鿡�� �˻��ϱ� ����.
		table_view.getItems().clear();
		
		
		
		//�ʵ忡�� ���� ��.
		String name = text_name.getText();
		//if(name.isEmpty()) {name=null;}
		String phone = text_phone.getText();
		String regin =(String) choice_region.getSelectionModel().getSelectedItem();
		//if(regin.isEmpty()) {regin=null;}
		String address ;
		String product =(String) choice_product.getSelectionModel().getSelectedItem();
		Integer amount ;

	
		
		//����ճ� �ƴϳĿ� ���� sql ���� ��ɹ�.

		try {
			
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		AppStart.conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/project?useSSL=false&serverTimezone=UTC","an", "a1641418");
		
		
		AppStart.stmt = AppStart.conn.createStatement();
		//ResultSet rs = AppDB.stmt.executeQuery("select *from project where name = '"+name+"' and" );   

	
		//�˻� ���ǿ� ���� �޸� �ϴ� sql��.
		
		ResultSet rs =null;
		
	        //�ƹ��͵� �Է� �������� ��üDB�ҷ��帲
		   if(name.isEmpty()&&phone.isEmpty()&&regin.equals("����")&&product.equals("����")) {
			AppStart.DB();
		    }
		
		    //������� �Է½� �˻�
		     if(!name.isEmpty()&&phone.isEmpty()) {
		      rs = AppStart.stmt.executeQuery("select *from project where name = '"+name+"'"); 
	         }
		 	
		    if(!name.isEmpty()&&!phone.isEmpty()) {
			      rs = AppStart.stmt.executeQuery("select *from project where name = '"+name+"' and phone ='"+phone+"'"); 
		        }
		    if(!name.isEmpty()&&!phone.isEmpty()&&!regin.equals("����")) {
			      rs = AppStart.stmt.executeQuery("select *from project where name = '"+name+"' and phone ='"+phone+"' and regin ='"+regin+"'" ); 
		        }
		    if(!name.isEmpty()&&!phone.isEmpty()&&!regin.equals("����")&&!product.equals("����")) {
			      rs = AppStart.stmt.executeQuery("select *from project where name = '"+name+"' and phone ='"+phone+"' and regin ='"+regin+"' and product ='"+product+"'"); 
		        }
		    
		    
		    
		    //1�� �� �Է½� �˻�
		  if(name.isEmpty()&&!phone.isEmpty()) {
				 rs = AppStart.stmt.executeQuery("select *from project where phone = '"+phone+"' ORDER BY name ASC"); 
		    }
		    
		   if(name.isEmpty()&&!regin.equals("����")&&phone.isEmpty()){
			  rs = AppStart.stmt.executeQuery("select *from project where regin = '"+regin+"' ORDER BY name ASC"); 
		      }
	        
		   if(name.isEmpty()&&phone.isEmpty()&&!product.equals("����")){
				  rs = AppStart.stmt.executeQuery("select *from project where product = '"+product+"' ORDER BY name ASC");
			      }
		
		
		   
		   //�� ��ǰ 
		   if(name.isEmpty()&&!phone.isEmpty()&&regin.equals("����")&&!product.equals("����")) {
			      rs = AppStart.stmt.executeQuery("select *from project where phone ='"+phone+"'and product ='"+product+"'"); 
		        }
		   //���� ��ǰ 
		   if(name.isEmpty()&&phone.isEmpty()&&!regin.equals("����")&&!product.equals("����")) {
			      rs = AppStart.stmt.executeQuery("select *from project where regin ='"+regin+"'and product ='"+product+"' ORDER BY name ASC"); 
		        }
		   //�̸� ��ǰ 
		   if(!name.isEmpty()&&phone.isEmpty()&&regin.equals("����")&&!product.equals("����")) {
			      rs = AppStart.stmt.executeQuery("select *from project where name ='"+name+"'and product ='"+product+"'"); 
		        }
		   //�̸� ����
		   if(!name.isEmpty()&&phone.isEmpty()&&!regin.equals("����")&&product.equals("����")) {
			      rs = AppStart.stmt.executeQuery("select *from project where name ='"+name+"'and regin ='"+regin+"'"); 
		        }
		   
		   
		   
		   //�̸� ���� ��ǰ
		   if(!name.isEmpty()&&phone.isEmpty()&&!regin.equals("����")&&!product.equals("����")) {
			      rs = AppStart.stmt.executeQuery("select *from project where name = '"+name+"' and regin ='"+regin+"' and product ='"+product+"' ORDER BY name ASC"); 
		        }
		   //�� ���� ��ǰ
		   if(name.isEmpty()&&!phone.isEmpty()&&!regin.equals("����")&&!product.equals("����")) {
			      rs = AppStart.stmt.executeQuery("select *from project where phone = '"+phone+"' and regin ='"+regin+"' and product ='"+product+"'"); 
		        }
		 
		  
				
		 
		
		//DB���� �ҷ��� ����
		while(rs.next()) {
			name = rs.getString("name");
			phone = rs.getString("phone");
			regin = rs.getString("regin");
			address = rs.getString("address");
			product = rs.getString("product");
			amount = rs.getInt("amount");
			
		
			User user = new User();
			user.setName(name);
			user.setPhone(phone);
			user.setRegin(regin);
			user.setAddress(address);
			user.setProduct(product);
			user.setAmount(amount);
		 
			AppController.list.add(user);
		}
		 
		
		
		 } catch (Exception e) {
				// TODO: handle exception
		}
		
		product_Amount();

	}


	// ���� ��ư Ŭ�� DB�� ���̺� �ڷ� ����.
	private void handleDelectAction(ActionEvent event) {
		// TODO Auto-generated method stub
		
		try {
			
	
		//DB���� ��� ����
		String phone = table_view.getSelectionModel().getSelectedItem().getPhone();
	
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		AppStart.conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/project?useSSL=false&serverTimezone=UTC","an", "a1641418");
		
		
		AppStart.stmt = AppStart.conn.createStatement();
		@SuppressWarnings("unused")
		int rs = AppStart.stmt.executeUpdate("delete from project where phone='"+phone+"'");   
				//"insert into(name, phone, regin, address, product, amount) values('"+name+"', '"+phone+"', "+"'"+regin+"', '"+address+"', '"+product+"', '"+amount+"'");                                    
	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		//Table���ø�� ����
		int selected =table_view.getSelectionModel().getSelectedIndex();
		table_view.getItems().remove(selected);
		
		//��ǰ�� �� ����
		product_Amount();
		
		
	}

	//�߰� ��ư Ŭ�� ���̾�α� ������ DB������Ʈ 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void handleAddaction(ActionEvent event)  {
		// TODO Auto-generated method stub
		try {
			
		
		//���̾� �α�â ���.
		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.initOwner(btn_add.getScene().getWindow());
		dialog.setTitle("�߰�");
		
		
		Parent parent = FXMLLoader.load(getClass().getResource("app_add.fxml"));
		Scene scene = new Scene(parent);
		dialog.setScene(scene);
		dialog.setResizable(false);
		dialog.show();
		
	
		//���� ��� �߰�
		ChoiceBox<Object> choiceRegin = (ChoiceBox)parent.lookup("#choiceRegin");
		choiceRegin.setItems(FXCollections.observableArrayList(
				"����", "����", "�λ�",new Separator(),"����"
				));
		choiceRegin.getSelectionModel().select(4);
		//��ǰ ��� �߰�
		ChoiceBox<Object> choiceProduct = (ChoiceBox)parent.lookup("#choiceProduct");
		choiceProduct.setItems(FXCollections.observableArrayList(
				"��ǰA", "��ǰB", "��ǰC",new Separator(),"����"
				));
		choiceProduct.getSelectionModel().select(4);
		
		
		
		//���̾�α׿��� ��� ������ �پ� �α�â ����.
		Button btnCancel = (Button)parent.lookup("#dialog_close");
		btnCancel.setOnAction(e->dialog.close());
		
		
		
		TextField textname = (TextField)parent.lookup("#textName");
		TextField textphone = (TextField)parent.lookup("#textPhone");
		@SuppressWarnings("unused")
		ChoiceBox choiceregin = (ChoiceBox)parent.lookup("#choiceRegin");
		TextField textaddress = (TextField)parent.lookup("#textAddress");
		ChoiceBox choiceproduct = (ChoiceBox)parent.lookup("#choiceProduct");
		TextField textamount = (TextField)parent.lookup("#textAmount");
		
		
		
		
		
		
		//���̾�α׿��� Ȯ�� ��ư
		Button btnSave = (Button)parent.lookup("#dialog_save");
		btnSave.setOnAction(e->{
			try {
				
			
				
				String name1 = textname.getText();
				String phone1 = textphone.getText();
				String regin1 = (String) choiceRegin.getSelectionModel().getSelectedItem();
				String address1 = textaddress.getText();
				String product1 = (String) choiceproduct.getSelectionModel().getSelectedItem();
				String amount1 = textamount.getText();
				
				
				
			
			
				
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				AppStart.conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/project?useSSL=false&serverTimezone=UTC","an", "a1641418");
			
				
				AppStart.stmt = AppStart.conn.createStatement();
				@SuppressWarnings("unused")
				int rs = AppStart.stmt.executeUpdate("insert project(name, phone, regin, address, product, amount) values('"+name1+"', '"+phone1+"', "+"'"+regin1+"', '"+address1+"', '"+product1+"', '"+amount1+"')");   
						//"insert into(name, phone, regin, address, product, amount) values('"+name+"', '"+phone+"', "+"'"+regin+"', '"+address+"', '"+product+"', '"+amount+"'");                                    
			
				
				
				User user = new User(name1, phone1, regin1, address1, product1, Integer.parseInt(amount1));
				list.add(0, user);  //������ ��Ʃ��Ʈ ��ü ����Ʈ�� ����
				dialog.close();
				
				
				product_Amount();
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
			
			}
		});
		
		
		// �� �Է��� ���� ������ ����
		
		textname.setOnAction(btnSave.getOnAction());
		textphone.setOnAction(btnSave.getOnAction());
		textaddress.setOnAction(btnSave.getOnAction());
		textamount.setOnAction(btnSave.getOnAction());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
			
		
	}


	
	//Choice_Box
	public void Choice_Box_region() {
		
		choice_region.setItems(FXCollections.observableArrayList(
				"����","����","�λ�",new Separator(),"����"
				));
		//���̽� �ڽ� �⺻������
		choice_region.getSelectionModel().select(4);
	}

	public void Choice_Box_product() {
		
		choice_product.setItems(FXCollections.observableArrayList(
				"��ǰA","��ǰB","��ǰC",new Separator(),"����"
				));
		
		//���̽� �ڽ� �⺻������
		choice_product.getSelectionModel().select(4);
	}
	
	
	
	
	//list����� TableView�� ���
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void startTableView() {
		for(int i =0 ; i<list.size(); i++) {
			TableColumn tc1 = table_view.getColumns().get(0);
			tc1.setCellValueFactory(new PropertyValueFactory("name"));
			
			TableColumn	 tc = table_view.getColumns().get(1);
			tc.setCellValueFactory(new PropertyValueFactory("phone"));
			
			 tc = table_view.getColumns().get(2);
			tc.setCellValueFactory(new PropertyValueFactory("regin"));
			
			 tc = table_view.getColumns().get(3);
			tc.setCellValueFactory(new PropertyValueFactory("address"));
			
			 tc = table_view.getColumns().get(4);
				tc.setCellValueFactory(new PropertyValueFactory("product"));
		
			tc = table_view.getColumns().get(5);
			 tc.setCellValueFactory(new PropertyValueFactory("amount"));
			 
			 table_view.setItems(list);
			  
			
			}
		
		
	}
	
	
	//��ǰ�� �Ѱ��� 
	private void product_Amount() {
		// TODO Auto-generated method stub
	
		
		pdcA_amount = 0;
		pdcB_amount = 0;
		pdcC_amount = 0;
		
		for(int i=0; i<list.size();i++) {
		
			if(list.get(i).getProduct().equals("��ǰA")) {
				pdcA_amount+=(Integer)list.get(i).getAmount();
			}
			
			
			if(list.get(i).getProduct().equals("��ǰB")) {
				pdcB_amount+=list.get(i).getAmount();
			}
			
			if(list.get(i).getProduct().equals("��ǰC")) {
				pdcC_amount+=list.get(i).getAmount();
			}
		}
		
			
		String productA_Lable = Integer.toString(pdcA_amount);
		productA.setText(productA_Lable);
		
		String productB_Lable = Integer.toString(pdcB_amount);
		productB.setText(productB_Lable);
		
		String productC_Lable = Integer.toString(pdcC_amount);
		productC.setText(productC_Lable);
	
	}

	
	
}




