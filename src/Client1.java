import fr.lip6.move.pnml.ptnet.hlapi.PageHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PlaceHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PositionHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.AnnotationGraphicsHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.DimensionHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.NameHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.NodeGraphicsHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.OffsetHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PTMarkingHLAPI;
import fr.lip6.move.pnml.framework.utils.exception.InvalidIDException;
import fr.lip6.move.pnml.framework.utils.exception.VoidRepositoryException;

public class Client1 {
	
	private int id;
	private PlaceHLAPI init;
	private PlaceHLAPI att;
	
	public Client1(int id, PageHLAPI clientpage, int x, int y) {
		this.id = id;
		try {

			init = new PlaceHLAPI("init"+id,clientpage);
			init.setInitialMarkingHLAPI(new PTMarkingHLAPI(1L));
			NodeGraphicsHLAPI pg1 = new NodeGraphicsHLAPI(init);
			PositionHLAPI pos1 = new PositionHLAPI(x,y,pg1);
			DimensionHLAPI dim1 = new DimensionHLAPI(25,25,pg1);
			OffsetHLAPI o1 = new OffsetHLAPI(-12,-30,new AnnotationGraphicsHLAPI(new NameHLAPI(init.getId(),init)));
			
			att  = new PlaceHLAPI("att"+id,clientpage);		
			NodeGraphicsHLAPI pg2 = new NodeGraphicsHLAPI(att);
			PositionHLAPI pos2 = new PositionHLAPI(x,y+50,pg2);
			DimensionHLAPI dim2 = new DimensionHLAPI(25,25,pg2);
			OffsetHLAPI o2 = new OffsetHLAPI(-12,-30,new AnnotationGraphicsHLAPI(new NameHLAPI(att.getId(),att)));
			
			//System.out.println("Client "+id+" created");
			
		} catch (InvalidIDException e) {
			e.printStackTrace();
		} catch (VoidRepositoryException e) {
			e.printStackTrace();	
		}
	}
	
	public int get_clientid() {
		return id;
	}
	
	public PlaceHLAPI get_init() {
		return init;
	}
	
	public PlaceHLAPI get_att() {
		return att;
	}

}