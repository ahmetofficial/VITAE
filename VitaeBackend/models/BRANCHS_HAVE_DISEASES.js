/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('BRANCHS_HAVE_DISEASES', {
    branch_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'BRANCHS',
        key: 'branch_id'
      }
    },
    disease_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'DISEASES',
        key: 'disease_id'
      }
    }
  }, {
    tableName: 'BRANCHS_HAVE_DISEASES'
  });
};
