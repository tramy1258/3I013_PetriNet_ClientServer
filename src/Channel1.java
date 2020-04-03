import fr.lip6.move.pnml.ptnet.hlapi.PageHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PlaceHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PositionHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.TransitionHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.AnnotationGraphicsHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.ArcHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.DimensionHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.NameHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.NodeGraphicsHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.OffsetHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PTMarkingHLAPI;
import fr.lip6.move.pnml.framework.utils.exception.InvalidIDException;
import fr.lip6.move.pnml.framework.utils.exception.VoidRepositoryException;

public class Channel1 {

	private PlaceHLAPI sys;
	private PlaceHLAPI[][] req_rep;
	private TransitionHLAPI[][] send_rec;
	private ArcHLAPI[][] sys_arcs;
	
	public Channel1(PageHLAPI syspage, Client1[] list, int d) {
		try {

			sys = new PlaceHLAPI("sys",syspage);			
			sys.setInitialMarkingHLAPI(new PTMarkingHLAPI(1L));
			NodeGraphicsHLAPI pg = new NodeGraphicsHLAPI(sys);
			PositionHLAPI pos = new PositionHLAPI(d/2,10,pg);
			DimensionHLAPI dim = new DimensionHLAPI(25,25,pg);
			NameHLAPI n = new NameHLAPI(sys.getId(),sys);
			AnnotationGraphicsHLAPI ag = new AnnotationGraphicsHLAPI(n);
			OffsetHLAPI o = new OffsetHLAPI(-12,-30,ag);
			
			req_rep = new PlaceHLAPI[list.length][2];
			send_rec = new TransitionHLAPI[list.length][2];
			
			sys_arcs = new ArcHLAPI[list.length][4];
			
			for (int i=0; i<list.length; i++) {
				req_rep[i][0] = new PlaceHLAPI("sys_req"+list[i].get_clientid(),syspage);
				NodeGraphicsHLAPI pg1 = new NodeGraphicsHLAPI(req_rep[i][0]);
				PositionHLAPI pos1 = new PositionHLAPI((d/list.length)/2-40+i*d/list.length,200,pg1);
				DimensionHLAPI dim1 = new DimensionHLAPI(25,25,pg1);
				OffsetHLAPI o1 = new OffsetHLAPI(-12,-30,new AnnotationGraphicsHLAPI(new NameHLAPI(req_rep[i][0].getId(),req_rep[i][0])));
				
				req_rep[i][1] = new PlaceHLAPI("sys_rep"+list[i].get_clientid(),syspage);
				NodeGraphicsHLAPI pg2 = new NodeGraphicsHLAPI(req_rep[i][1]);
				PositionHLAPI pos2 = new PositionHLAPI((d/list.length)/2+40+i*d/list.length,200,pg2);
				DimensionHLAPI dim2 = new DimensionHLAPI(25,25,pg2);
				OffsetHLAPI o2 = new OffsetHLAPI(-12,-30,new AnnotationGraphicsHLAPI(new NameHLAPI(req_rep[i][1].getId(),req_rep[i][1])));
				
				send_rec[i][0] = new TransitionHLAPI("send_req"+list[i].get_clientid(),syspage);
				NodeGraphicsHLAPI pg3 = new NodeGraphicsHLAPI(send_rec[i][0]);
				PositionHLAPI pos3 = new PositionHLAPI((d/list.length)/2-40+i*d/list.length,140,pg3);
				DimensionHLAPI dim3 = new DimensionHLAPI(10,25,pg3);
				OffsetHLAPI o3 = new OffsetHLAPI(-12,-30,new AnnotationGraphicsHLAPI(new NameHLAPI(send_rec[i][0].getId(),send_rec[i][0])));
				
				send_rec[i][1] = new TransitionHLAPI("rec_rep"+list[i].get_clientid(),syspage);
				NodeGraphicsHLAPI pg4 = new NodeGraphicsHLAPI(send_rec[i][1]);
				PositionHLAPI pos4 = new PositionHLAPI((d/list.length)/2+40+i*d/list.length,140,pg4);
				DimensionHLAPI dim4 = new DimensionHLAPI(10,25,pg4);
				OffsetHLAPI o4 = new OffsetHLAPI(-12,-30,new AnnotationGraphicsHLAPI(new NameHLAPI(send_rec[i][1].getId(),send_rec[i][1])));
				
				sys_arcs[i][0] = new ArcHLAPI("cna0of"+i,sys,send_rec[i][0],syspage);
				sys_arcs[i][1] = new ArcHLAPI("cna1of"+i,send_rec[i][0],req_rep[i][0],syspage);
				sys_arcs[i][2] = new ArcHLAPI("cna2of"+i,req_rep[i][1],send_rec[i][1],syspage);
				sys_arcs[i][3] = new ArcHLAPI("cna3of"+i,send_rec[i][1],sys,syspage);
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
