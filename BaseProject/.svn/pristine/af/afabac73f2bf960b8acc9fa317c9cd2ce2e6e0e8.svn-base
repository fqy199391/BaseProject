package base.wujiang.com.baseproject.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * 概要说明 : 智慧消防单兵终端拷贝.  <br>
 * 详细说明 : fragment工具类.  <br>
 * 创建时间 : 2017-7-13 上午9:53:57 <br>
 * @author by luyonglong
 */
public class FragmentUtil
{
    // 是否用替换方式还是隐藏方式
    public static void addFragment(FragmentActivity fragmentActivity, int placeId, Fragment fragment)
    {
        fragmentActivity.getSupportFragmentManager().beginTransaction().replace(placeId, fragment).commit();
    }

    public static void removeFragment(FragmentActivity fragmentActivity, Fragment fragment)
    {
        fragmentActivity.getSupportFragmentManager().beginTransaction().remove(fragment).commit();
    }

    public static void hideFragment(FragmentActivity fragmentActivity, Fragment fragment)
    {
        fragmentActivity.getSupportFragmentManager().beginTransaction().hide(fragment).commit();
    }

    private void hideFragments(FragmentTransaction ft, int index)
    {
        for (int i = 0; i < fragments.length; i++)
        {
            if (addFragmentsPos[i] == 0)
            {
                continue;
            }
            if (index == i)
            {
                continue;
            }
            Fragment fi = fragments[i];
            ft.hide(fi);
        }
    }

    public Fragment[] fragments;

    private byte[] addFragmentsPos;

    private int contentId;

    private int inAnim;

    private int outAnim;

    public boolean isUseReplace = false;

    public FragmentUtil(int contentId, Fragment[] fragments, int... intAnims)
    {
        this.contentId = contentId;
        this.fragments = fragments;
        addFragmentsPos = new byte[fragments.length];
        if (intAnims.length > 0)
        {
            inAnim = intAnims[0];
            outAnim = intAnims[1];
        }
    }

    public void showFragment(FragmentActivity ctx, int index)
    {
        FragmentManager fm = ctx.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (isUseReplace)
        {
            replaceFragment(ft, index);
        }
        else
        {
            if (inAnim != 0 && outAnim != 0)
            {
                ft.setCustomAnimations(inAnim, outAnim);
            }
            hideFragments(ft, index);
            showFragment(ft, index);
        }
        ft.commit();

    }

    public boolean isFragmentAdd(int index)
    {
        if (addFragmentsPos[index] == 1)
        {
            return true;
        }
        return false;
    }

    private void showFragment(FragmentTransaction ft, int index)
    {
        if (addFragmentsPos[index] == 0)
        {
            addFragmentsPos[index] = 1;
            ft.add(contentId, fragments[index]);
        }
        else
        {
            ft.show(fragments[index]);
        }
    }

    private void replaceFragment(FragmentTransaction ft, int index)
    {
        ft.replace(contentId, fragments[index]);
    }
}
