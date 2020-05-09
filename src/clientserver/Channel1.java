package clientserver;
import fr.lip6.move.pnml.ptnet.hlapi.PageHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PlaceHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PositionHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.TransitionHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.AnnotationGraphicsHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.ArcHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.ArcGraphicsHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.DimensionHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.NameHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.NodeGraphicsHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.OffsetHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PTMarkingHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.LineHLAPI;
import fr.lip6.move.pnml.ptnet.CSS2Color;
import fr.lip6.move.pnml.framework.utils.exception.InvalidIDException;
import fr.lip6.move.pnml.framework.utils.exception.VoidRepositoryException;

public class Channel1 {

	private PlaceHLAPI sys;
	private PlaceHLAPI[][] req_rep;
	private TransitionHLAPI[][] send_rec;
	
	public Channel1(PageHLAPI syspage, Client1[] list, int d) {
		try {

			sys = new PlaceHLAPI("sys",syspage);			
			sys.setInitialMarkingHLAPI(new PTMarkingHLAPI(1L));
			NodeGraphicsHLAPI pg = new NodeGraphicsHLAPI(sys);
			PositionHLAPI pos = new PositionHLAPI(d/2,10,pg);
			DimensionHLAPI dim = new DimensionHLAPI(25,25,pg);
			OffsetHLAPI o = new OffsetHLAPI(-12,-30,new AnnotationGraphicsHLAPI(new NameHLAPI(sys.getId(),sys)));
			OffsetHLAPI omk = new OffsetHLAPI(-5,-10,new AnnotationGraphicsHLAPI(sys.getInitialMarkingHLAPI()));
			LineHLAPI l = new LineHLAPI(pg);
			l.setColorHLAPI(CSS2Color.OLIVE);
			
			req_rep = new PlaceHLAPI[list.length][2];
			send_rec = new TransitionHLAPI[list.length][2];
			
			for (int i=0; i<list.length; i++) {
				req_rep[i][0] = new PlaceHLAPI("sys_req"+list[i].get_clientid(),syspage);
				NodeGraphicsHLAPI pg1 = new NodeGraphicsHLAPI(req_rep[i][0]);
				PositionHLAPI pos1 = new PositionHLAPI((d/list.length)/2-40+i*d/list.length,200,pg1);
				DimensionHLAPI dim1 = new DimensionHLAPI(25,25,pg1);
				OffsetHLAPI o1 = new OffsetHLAPI(-20,-30,new AnnotationGraphicsHLAPI(new NameHLAPI(req_rep[i][0].getId(),req_rep[i][0])));
				LineHLAPI l1 = new LineHLAPI(pg1);
				l1.setColorHLAPI(CSS2Color.OLIVE);
				
				req_rep[i][1] = new PlaceHLAPI("sys_rep"+list[i].get_clientid(),syspage);
				NodeGraphicsHLAPI pg2 = new NodeGraphicsHLAPI(req_rep[i][1]);
				PositionHLAPI pos2 = new PositionHLAPI((d/list.length)/2+40+i*d/list.length,200,pg2);
				DimensionHLAPI dim2 = new DimensionHLAPI(25,25,pg2);
				OffsetHLAPI o2 = new OffsetHLAPI(-12,-30,new AnnotationGraphicsHLAPI(new NameHLAPI(req_rep[i][1].getId(),req_rep[i][1])));
				LineHLAPI l2 = new LineHLAPI(pg2);
				l2.setColorHLAPI(CSS2Color.OLIVE);
				
				send_rec[i][0] = new TransitionHLAPI("send_req"+list[i].get_clientid(),syspage);
				NodeGraphicsHLAPI pg3 = new NodeGraphicsHLAPI(send_rec[i][0]);
				PositionHLAPI pos3 = new PositionHLAPI((d/list.length)/2-40+i*d/list.length,140,pg3);
				DimensionHLAPI dim3 = new DimensionHLAPI(10,25,pg3);
				OffsetHLAPI o3 = new OffsetHLAPI(-30,-30,new AnnotationGraphicsHLAPI(new NameHLAPI(send_rec[i][0].getId(),send_rec[i][0])));
				LineHLAPI l3 = new LineHLAPI(pg3);
				l3.setColorHLAPI(CSS2Color.OLIVE);
				
				send_rec[i][1] = new TransitionHLAPI("rec_rep"+list[i].get_clientid(),syspage);
				NodeGraphicsHLAPI pg4 = new NodeGraphicsHLAPI(send_rec[i][1]);
				PositionHLAPI pos4 = new PositionHLAPI((d/list.length)/2+40+i*d/list.length,140,pg4);
				DimensionHLAPI dim4 = new DimensionHLAPI(10,25,pg4);
				OffsetHLAPI o4 = new OffsetHLAPI(-15,-30,new AnnotationGraphicsHLAPI(new NameHLAPI(send_rec[i][1].getId(),send_rec[i][1])));
				LineHLAPI l4 = new LineHLAPI(pg4);
				l4.setColorHLAPI(CSS2Color.OLIVE);
				
				ArcHLAPI a0 = new ArcHLAPI("cna0of"+i,sys,send_rec[i][0],syspage);
				LineHLAPI al1 = new LineHLAPI(new ArcGraphicsHLAPI(a0));
				al1.setColorHLAPI(CSS2Color.OLIVE);
				ArcHLAPI a1 = new ArcHLAPI("cna1of"+i,send_rec[i][0],req_rep[i][0],syspage);
				LineHLAPI al2 = new LineHLAPI(new ArcGraphicsHLAPI(a1));
				al2.setColorHLAPI(CSS2Color.OLIVE);
				ArcHLAPI a2 = new ArcHLAPI("cna2of"+i,req_rep[i][1],send_rec[i][1],syspage);
				LineHLAPI al3 = new LineHLAPI(new ArcGraphicsHLAPI(a2));
				al3.setColorHLAPI(CSS2Color.OLIVE);
				ArcHLAPI a3 = new ArcHLAPI("cna3of"+i,send_rec[i][1],sys,syspage);
				LineHLAPI al4 = new LineHLAPI(new ArcGraphicsHLAPI(a3));
				al4.setColorHLAPI(CSS2Color.OLIVE);
			}
			
			//System.out.println("Channel created");

		} catch (InvalidIDException e) {
			e.printStackTrace();
		} catch (VoidRepositoryException e) {
			e.printStackTrace();	
		}
	}	
	
	public PlaceHLAPI get_sysreq (int indlistclient) {
		return req_rep[indlistclient][0];
	}
	
	public PlaceHLAPI get_sysrep (int indlistclient) {
		return req_rep[indlistclient][1];
	}
	
	public TransitionHLAPI get_send (int indlistclient) {
		return send_rec[indlistclient][0];
	}
	
	public TransitionHLAPI get_rec (int indlistclient) {
		return send_rec[indlistclient][1];
	}
}
