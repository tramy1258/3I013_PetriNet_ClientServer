package clientserver;
import fr.lip6.move.pnml.ptnet.hlapi.PageHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.TransitionHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PlaceHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PositionHLAPI;
import fr.lip6.move.pnml.ptnet.CSS2Color;
import fr.lip6.move.pnml.ptnet.hlapi.AnnotationGraphicsHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.ArcHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.ArcGraphicsHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.DimensionHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.LineHLAPI;
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
	private PlaceHLAPI cc;
	
	public Client1(int id, PageHLAPI clientpage, int x, int y) {
		this.id = id;
		try {

			init = new PlaceHLAPI("init"+id,clientpage);
			init.setInitialMarkingHLAPI(new PTMarkingHLAPI(1L));
			NodeGraphicsHLAPI pg1 = new NodeGraphicsHLAPI(init);
			PositionHLAPI pos1 = new PositionHLAPI(x,y,pg1);
			DimensionHLAPI dim1 = new DimensionHLAPI(25,25,pg1);
			OffsetHLAPI o1 = new OffsetHLAPI(-12,-30,new AnnotationGraphicsHLAPI(new NameHLAPI(init.getId(),init)));
			OffsetHLAPI omk = new OffsetHLAPI(-5,-10,new AnnotationGraphicsHLAPI(init.getInitialMarkingHLAPI()));
			LineHLAPI l1 = new LineHLAPI(pg1);
			l1.setColorHLAPI(CSS2Color.ORANGE);
			
			att = new PlaceHLAPI("att"+id,clientpage);		
			NodeGraphicsHLAPI pg2 = new NodeGraphicsHLAPI(att);
			PositionHLAPI pos2 = new PositionHLAPI(x,y+50,pg2);
			DimensionHLAPI dim2 = new DimensionHLAPI(25,25,pg2);
			OffsetHLAPI o2 = new OffsetHLAPI(-12,-30,new AnnotationGraphicsHLAPI(new NameHLAPI(att.getId(),att)));
			LineHLAPI l2 = new LineHLAPI(pg2);
			l2.setColorHLAPI(CSS2Color.ORANGE);
			
			cc = new PlaceHLAPI("cc"+id,clientpage);		
			NodeGraphicsHLAPI pg3 = new NodeGraphicsHLAPI(cc);
			PositionHLAPI pos3 = new PositionHLAPI(x,y-50,pg3);
			DimensionHLAPI dim3 = new DimensionHLAPI(25,25,pg3);
			OffsetHLAPI o3 = new OffsetHLAPI(-12,-30,new AnnotationGraphicsHLAPI(new NameHLAPI(cc.getId(),cc)));
			LineHLAPI l3 = new LineHLAPI(pg3);
			l3.setColorHLAPI(CSS2Color.ORANGE);
			
			TransitionHLAPI calcul = new TransitionHLAPI("calcul"+id,clientpage);		
			NodeGraphicsHLAPI pg4 = new NodeGraphicsHLAPI(calcul);
			PositionHLAPI pos4 = new PositionHLAPI(x-40,y-30,pg4);
			DimensionHLAPI dim4 = new DimensionHLAPI(10,25,pg4);
			OffsetHLAPI o4 = new OffsetHLAPI(-12,-30,new AnnotationGraphicsHLAPI(new NameHLAPI(calcul.getId(),calcul)));
			LineHLAPI l4 = new LineHLAPI(pg4);
			l4.setColorHLAPI(CSS2Color.ORANGE);
			
			ArcHLAPI a0 = new ArcHLAPI("arc1"+id,cc,calcul,clientpage);
			LineHLAPI al0 = new LineHLAPI(new ArcGraphicsHLAPI(a0));
			al0.setColorHLAPI(CSS2Color.ORANGE);
			ArcHLAPI a1 = new ArcHLAPI("arc2"+id,calcul,init,clientpage);
			LineHLAPI al1 = new LineHLAPI(new ArcGraphicsHLAPI(a1));
			al1.setColorHLAPI(CSS2Color.ORANGE);
			
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
	
	public PlaceHLAPI get_cc() {
		return cc;
	}
}