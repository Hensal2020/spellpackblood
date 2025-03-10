package hensal.spellpackblood.spell;

import electroblob.wizardry.item.SpellActions;
import electroblob.wizardry.spell.SpellRay;
import electroblob.wizardry.util.EntityUtils;
import electroblob.wizardry.util.SpellModifiers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import hensal.spellpackblood.MySpellPack;
import hensal.spellpackblood.registry.MySpellPackPotions;

public class BloodBurst extends SpellRay
{

    public BloodBurst()
    {
        super(MySpellPack.MODID, "blood_burst", SpellActions.POINT, false);
    }

    @Override
    protected boolean onEntityHit(World world, Entity target, Vec3d hit, EntityLivingBase caster, Vec3d origin, int ticksInUse, SpellModifiers modifiers)
    {

        if (EntityUtils.isLiving(target) && !world.isRemote && target instanceof EntityLivingBase)
        {
            EntityLivingBase starget = (EntityLivingBase) target;
            if (starget.isPotionActive(MySpellPackPotions.bmarked))
            {
                float s = (float) starget.getActivePotionEffect(MySpellPackPotions.bmarked).getAmplifier() + 1;
                world.createExplosion(null, starget.posX, starget.posY + 1.5, starget.posZ, s, false);
                ((EntityLivingBase) target).removePotionEffect(MySpellPackPotions.bmarked);
                return true;
            } else
            {
                return false;
            }
        }
        return false;
    }

    @Override
    protected boolean onBlockHit(World world, BlockPos pos, EnumFacing side, Vec3d hit, EntityLivingBase caster, Vec3d origin, int ticksInUse, SpellModifiers modifiers)
    {
        return false;
    }

    @Override
    protected boolean onMiss(World world, EntityLivingBase caster, Vec3d origin, Vec3d direction, int ticksInUse, SpellModifiers modifiers)
    {
        return false;
    }
}
