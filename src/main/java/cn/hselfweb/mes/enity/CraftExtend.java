package cn.hselfweb.mes.enity;

import lombok.Data;

import java.util.List;




@Data
public class CraftExtend implements java.io.Serializable,Comparable<CraftExtend>{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private	int id;
		private int cbId;
		private int pid;
		private Integer paId;
		private Integer pbId;
		private int priority;
		private CraftExtend extendCraftByAftEcId;
		private CraftExtend extendCraftByPreEcId;

		//===================算法所需=====================
		private Integer bmrpId;
		private String wpname;//所属工件的名称
		private Integer wpid;//所属工件的编号
		private String name;// 工序名称
		private String gname;// 工序名称
		private int time;// 加工时间
		private String device;// 占用设备
		private List<CraftExtend> list;//设备所在的工序序列
		private int gid;//工件中的第几道工序
		private int did;//在设备上位于第几道工序
		private double xiaolv;// 效率函数
		private int endtime;//本工序在设备上的结束时间
		private int bendtime;//本工序在设备上的结束时间

	/**
	 * Compares this object with the specified object for order.  Returns a
	 * negative integer, zero, or a positive integer as this object is less
	 * than, equal to, or greater than the specified object.
	 *
	 * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
	 * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
	 * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
	 * <tt>y.compareTo(x)</tt> throws an exception.)
	 *
	 * <p>The implementor must also ensure that the relation is transitive:
	 * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
	 * <tt>x.compareTo(z)&gt;0</tt>.
	 *
	 * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
	 * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
	 * all <tt>z</tt>.
	 *
	 * <p>It is strongly recommended, but <i>not</i> strictly required that
	 * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
	 * class that implements the <tt>Comparable</tt> interface and violates
	 * this condition should clearly indicate this fact.  The recommended
	 * language is "Note: this class has a natural ordering that is
	 * inconsistent with equals."
	 *
	 * <p>In the foregoing description, the notation
	 * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
	 * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
	 * <tt>0</tt>, or <tt>1</tt> according to whether the value of
	 * <i>expression</i> is negative, zero or positive.
	 *
	 * @param o the object to be compared.
	 * @return a negative integer, zero, or a positive integer as this object
	 * is less than, equal to, or greater than the specified object.
	 * @throws NullPointerException if the specified object is null
	 * @throws ClassCastException   if the specified object's type prevents it
	 *                              from being compared to this object.
	 */
	@Override
	public int compareTo(CraftExtend o) {
		return 0;
	}

	public void setBegintime(int i) {
	}

	public int getBegintime() {
		return 0;
	}
}
