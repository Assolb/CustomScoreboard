package gloomyfolken.hooklib.example;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import com.customscoreboard.client.events.RenderScoreboard;
import com.customscoreboard.utils.RgbaColor4f;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import gloomyfolken.hooklib.asm.At;
import gloomyfolken.hooklib.asm.Hook;
import gloomyfolken.hooklib.asm.InjectionPoint;
import gloomyfolken.hooklib.asm.ReturnCondition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TestHooks {
	
    @SideOnly(Side.CLIENT)
    @Hook(at = @At(point = InjectionPoint.HEAD), returnCondition = ReturnCondition.ALWAYS)
    public static void renderScoreboard(GuiIngame gui, ScoreObjective p_180475_1_, ScaledResolution p_180475_2_)
    {
    	Scoreboard scoreboard = p_180475_1_.getScoreboard();
        Collection<Score> collection = scoreboard.getSortedScores(p_180475_1_);
        List<Score> list = Lists.newArrayList(collection);

        if (list.size() > 15)
        {
            collection = Lists.newArrayList(Iterables.skip(list, collection.size() - 15));
        }
        else
        {
            collection = list;
        }

        if(Minecraft.getMinecraft().getRenderManager() == null) return;
        if(Minecraft.getMinecraft().getRenderManager().getFontRenderer() == null) return;
        if(p_180475_1_.getDisplayName() == null) return;
        int i = Minecraft.getMinecraft().getRenderManager().getFontRenderer().getStringWidth(p_180475_1_.getDisplayName());
        int i3 = 0;

        for (Score score : collection)
        {
            ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
            String s = ScorePlayerTeam.formatPlayerName(scoreplayerteam, score.getPlayerName()) + ": " + EnumChatFormatting.RED + score.getScorePoints();
            i = Math.max(i, Minecraft.getMinecraft().getRenderManager().getFontRenderer().getStringWidth(s));
            i3 = Math.max(i3, Minecraft.getMinecraft().getRenderManager().getFontRenderer().getStringWidth(s.split("\\*")[0]));
        }

        int i1 = collection.size() * Minecraft.getMinecraft().getRenderManager().getFontRenderer().FONT_HEIGHT;
        int j1 = p_180475_2_.getScaledHeight() / 2 + i1 / 3;
        int k1 = 3;
        int l1 = p_180475_2_.getScaledWidth() - i - k1;
        int j = 0;
        for (Score score1 : collection)
        {
            ++j;
            ScorePlayerTeam scoreplayerteam1 = scoreboard.getPlayersTeam(score1.getPlayerName());
            String s1 = ScorePlayerTeam.formatPlayerName(scoreplayerteam1, score1.getPlayerName()).split("\\*")[0];
            String s2 = ScorePlayerTeam.formatPlayerName(scoreplayerteam1, score1.getPlayerName()).split("\\*")[1];
            int k = j1 - j * (Minecraft.getMinecraft().getRenderManager().getFontRenderer().FONT_HEIGHT + 2);
            int l = p_180475_2_.getScaledWidth() - k1 + 2;
            RenderScoreboard.drawGradientAlphaRect(l1 - 10, k, l, k + Minecraft.getMinecraft().getRenderManager().getFontRenderer().FONT_HEIGHT + 2, new RgbaColor4f(0, 0, 0, 0.02f), new RgbaColor4f(0, 0, 0, 0.7f));
            //drawRect(l1 - 2, k, l, k + Minecraft.getMinecraft().getRenderManager().getFontRenderer().FONT_HEIGHT, 1342177280);
            Minecraft.getMinecraft().getRenderManager().getFontRenderer().drawString(s1, l1 + (i3 - Minecraft.getMinecraft().getRenderManager().getFontRenderer().getStringWidth(s1)), k, 553648127);
            Minecraft.getMinecraft().getRenderManager().getFontRenderer().drawString(s2, l - Minecraft.getMinecraft().getRenderManager().getFontRenderer().getStringWidth(s2) - 1, k, 553648127);

            if (j == collection.size())
            {
                String s3 = p_180475_1_.getDisplayName();
                RenderScoreboard.drawGradientAlphaRect(l1 - 10, k - Minecraft.getMinecraft().getRenderManager().getFontRenderer().FONT_HEIGHT - 3, l, k - 1, new RgbaColor4f(0, 0, 0, 0.02f), new RgbaColor4f(0, 0, 0, 0.7f));
                //drawRect(l1 - 2, k - Minecraft.getMinecraft().getRenderManager().getFontRenderer().FONT_HEIGHT - 1, l, k - 1, 1610612736);
                //drawRect(l1 - 2, k - 1, l, k, 1342177280);
                Minecraft.getMinecraft().getRenderManager().getFontRenderer().drawString(s3, l1 + i / 2 - Minecraft.getMinecraft().getRenderManager().getFontRenderer().getStringWidth(s3) / 2, k - Minecraft.getMinecraft().getRenderManager().getFontRenderer().FONT_HEIGHT - 1, 553648127);
            }
        }
    }
}